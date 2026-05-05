from flask import Flask, render_template, request, redirect, url_for, flash, abort
import database

app = Flask(__name__)
app.secret_key = 'dev-secret-key-change-in-production'

database.init_app(app)


@app.route('/')
def index():
    db = database.get_db()
    tasks = db.execute('SELECT * FROM tasks ORDER BY created_at DESC').fetchall()
    return render_template('index.html', tasks=tasks)


@app.route('/create', methods=['GET', 'POST'])
def create():
    if request.method == 'POST':
        title = request.form.get('title', '').strip()
        description = request.form.get('description', '').strip()
        if not title:
            flash('Title is required.', 'danger')
            return render_template('create.html')
        db = database.get_db()
        db.execute('INSERT INTO tasks (title, description) VALUES (?, ?)', (title, description))
        db.commit()
        flash('Task created successfully.', 'success')
        return redirect(url_for('index'))
    return render_template('create.html')


@app.route('/edit/<int:task_id>', methods=['GET', 'POST'])
def edit(task_id):
    db = database.get_db()
    task = db.execute('SELECT * FROM tasks WHERE id = ?', (task_id,)).fetchone()
    if task is None:
        abort(404)
    if request.method == 'POST':
        title = request.form.get('title', '').strip()
        description = request.form.get('description', '').strip()
        if not title:
            flash('Title is required.', 'danger')
            return render_template('edit.html', task=task)
        db.execute('UPDATE tasks SET title = ?, description = ? WHERE id = ?',
                   (title, description, task_id))
        db.commit()
        flash('Task updated successfully.', 'success')
        return redirect(url_for('index'))
    return render_template('edit.html', task=task)


@app.route('/delete/<int:task_id>', methods=['POST'])
def delete(task_id):
    db = database.get_db()
    task = db.execute('SELECT id FROM tasks WHERE id = ?', (task_id,)).fetchone()
    if task is None:
        abort(404)
    db.execute('DELETE FROM tasks WHERE id = ?', (task_id,))
    db.commit()
    flash('Task deleted.', 'warning')
    return redirect(url_for('index'))


@app.route('/toggle/<int:task_id>', methods=['POST'])
def toggle(task_id):
    db = database.get_db()
    task = db.execute('SELECT id, done FROM tasks WHERE id = ?', (task_id,)).fetchone()
    if task is None:
        abort(404)
    new_status = 0 if task['done'] else 1
    db.execute('UPDATE tasks SET done = ? WHERE id = ?', (new_status, task_id))
    db.commit()
    return redirect(url_for('index'))


if __name__ == '__main__':
    database.init_db()
    app.run(debug=True)
