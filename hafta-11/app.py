from flask import Flask, render_template, request, jsonify
from werkzeug.utils import secure_filename
import os
import base64

app = Flask(__name__)

# Configure
UPLOAD_FOLDER = 'uploads'
app.config['UPLOAD_FOLDER'] = UPLOAD_FOLDER

@app.route('/')
def index():
    return render_template('index.html')

@app.route('/2')
def index2():
    return render_template('index2.html')

@app.route('/3')
def index3():
    return render_template('index3.html')

@app.route('/4')
def index4():
    return render_template('index4.html')

# JSON ile upload
@app.route('/upload4', methods=['POST'])
def upload4_file():
    data = request.get_json()
    ogrenciNo = data.get('ogrenciNo', '')
    fileName = data.get('fileName', '')
    fileData = data.get('fileData', '')

    if not ogrenciNo:
        return jsonify({"error": "Öğrenci No post edilmedi."}, 400)
    
    if not fileName or not fileData:
        return jsonify({"error": "Dosya bilgileri eksik."}, 400)

    # Decode base64 file data
    try:
        decoded_file_data = base64.b64decode(fileData)
    except Exception as e:
        return jsonify({"error": "Dosya verisi geçersiz."}, 400)

    filename = ogrenciNo + "-" + secure_filename(fileName)
    file_path = os.path.join(app.config['UPLOAD_FOLDER'], filename)

    try:
        with open(file_path, 'wb') as f:
            f.write(decoded_file_data)
        return jsonify({"msg": "Dosya başarıyla yüklendi"}, 200)
    except Exception as e:
        return jsonify({"error": "Dosya kaydedilirken hata oluştu."}, 500)

# Multiform Upload
@app.route('/upload2', methods=['POST'])
def upload2_file():
    ogrenciNo = request.form.get('ogrenciNo', '')
    if not ogrenciNo:
        return jsonify({"error": "Öğrenci No post edilmedi."}, 400)
    
    # Check the post request has 'file'
    if 'dosya' not in request.files:
        return jsonify({"error": "Dosya yüklenmedi."}, 400)
    
    file = request.files['dosya']

    if file.filename == '':
        return jsonify({"error": "Hatalı dosya."}, 400)
    
    if file:
        filename = ogrenciNo + "-" + secure_filename(file.filename)
        file.save(os.path.join(app.config['UPLOAD_FOLDER'], filename))
        return jsonify({"msg": "Dosya başarıyla yüklendi"}, 200)


# Form elementi ile upload
@app.route('/upload', methods=['POST'])
def upload_file():
    # Check the post request has 'file'
    if 'dosya' not in request.files:
        return "No 'dosya' part in the form"
    
    file = request.files['dosya']

    if file.filename == '':
        return "No selected file"
    
    if file:
        filename = secure_filename(file.filename)
        file.save(os.path.join(app.config['UPLOAD_FOLDER'], filename))
        return f"Dosya '{filename}' yüklendi."


if __name__ == '__main__':
    app.run(debug=True)