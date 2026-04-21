from flask import Flask, render_template, request, jsonify
from werkzeug.utils import secure_filename
import os
import uuid
import base64

app = Flask(__name__)

# Configure
UPLOAD_FOLDER = 'uploads'
MAX_FILE_SIZE = 1 * 1024 * 1024
ALLOWED_EXTENSION = ".pdf"
app.config['UPLOAD_FOLDER'] = UPLOAD_FOLDER

# Eğer UPLOAD_FOLDER mevcut değilse, bu klasörü oluştur
if not os.path.exists(UPLOAD_FOLDER):
    os.makedirs(UPLOAD_FOLDER)

@app.route('/')
def index():
    return render_template('index.html')

@app.route('/2')
def index2():
    return render_template('index2.html')

# JSON ile upload
@app.route('/upload4', methods=['POST'])
def upload_file():
    data = request.get_json()
    ogrenciNo = data.get('ogrenciNo', '')
    fileName = data.get('fileName', '')
    fileData = data.get('fileData', '')

    if not ogrenciNo:
        return jsonify({"error": "Öğrenci No post edilmedi."}, 400)
    
    if not fileName or not fileData:
        return jsonify({"error": "Dosya bilgileri eksik."}, 400)

    # Dosya türü kontrolü
    extension = os.path.splitext(fileName)[1].lower()

    if extension != ALLOWED_EXTENSION:
        return jsonify({"error": "Sadece PDF dosyalar yüklenebilir."}, 400)

    # Decode base64 file data
    try:
        decoded_file_data = base64.b64decode(fileData)
    except Exception as e:
        return jsonify({"error": "Dosya verisi geçersiz."}, 400)

    # DOSYA BOYUTU KONTROLÜ
    if len(decoded_file_data) > MAX_FILE_SIZE:
        return jsonify({"error": "Dosya boyutu 1MB'tan büyük olmaza."}, 400)

    # Universal Unique ID (Benzersiz ID)
    unique_filename = f"{uuid.uuid4()}{extension}"
    print(f"Upload dosya adı: {unique_filename}")

    file_path = os.path.join(app.config['UPLOAD_FOLDER'], unique_filename)

    try:
        with open(file_path, 'wb') as f:
            f.write(decoded_file_data)
        return jsonify({"msg": "Dosya başarıyla yüklendi"}, 200)
    except Exception as e:
        return jsonify({"error": "Dosya kaydedilirken hata oluştu."}, 500)