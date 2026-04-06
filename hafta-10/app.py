from flask import Flask, render_template, jsonify, request
import sqlite3

app = Flask(__name__)

DB_NAME = 'tasks.db'

def init_db():
    conn = sqlite3.connect(DB_NAME)
    cursor = conn.cursor()
    cursor.execute("""
    CREATE TABLE IF NOT EXISTS tasks (
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        task TEXT NOT NULL,
        done INTEGER DEFAULT 0      
    )
    """)

    conn.commit()
    conn.close()

# Veritabanını oluştur
init_db()

# CRUD işlemleri: Create, Read, Update, Delete

def get_db_connection():
    conn = sqlite3.connect(DB_NAME)
    conn.row_factory = sqlite3.Row 
    return conn

# Yeni görev ekle
@app.route('/yeni', methods=['POST'])
def new_task():
    data = request.get_json()
    task = data.get('task', None)
    if task:
        conn = get_db_connection()
        conn.execute("INSERT INTO tasks (task) VALUES (?)", (task,))
        conn.commit()
        conn.close()
        return jsonify({"msg": "Task oluşturuldu"}), 200
    else:
        return jsonify({"msg": "Geçersiz görev verisi"}), 400

# Görevi tamamla (done = 1 yap)
@app.route('/complete/<int:task_id>', methods=['POST'])
def complete_task(task_id):
    conn = get_db_connection()
    conn.execute("UPDATE tasks SET done = 1 WHERE id = ?", (task_id,))
    conn.commit()
    conn.close()
    return jsonify({"msg": "Task tamamlandı", "id": task_id}), 200

# Tüm görevleri getir
@app.route('/tasks')
def get_tasks():
    conn = get_db_connection()
    tasks = conn.execute("SELECT * FROM tasks ORDER BY id DESC").fetchall()
    conn.close()
    tasks_list = [{"id": task["id"], "task": task["task"], "done": bool(task["done"])} for task in tasks]
    return jsonify(tasks_list), 200

# Görevi sil
@app.route('/delete/<int:task_id>', methods=['POST'])
def delete_task(task_id):
    conn = get_db_connection()
    conn.execute("DELETE FROM tasks WHERE id = ?", (task_id,))
    conn.commit()
    conn.close()
    return jsonify({"msg": "Task silindi", "id": task_id}), 200


@app.route('/')
def index():
    return render_template('index.html')

if __name__ == '__main__':
    app.run(debug=True)