from flask import Flask, render_template, url_for
import requests
import os

app = Flask(__name__)

image_number = 0

@app.route('/')
def index():
    global image_number
    image_number += 1

    image_url = "https://picsum.photos/1000/1000"
    image_response = requests.get(image_url, stream=True)

    # Ensure the request was successful
    if image_response.status_code == 200:
        image_path = os.path.join("static", f"downloaded_image_{image_number}.jpg")
        with open(image_path, 'wb') as image_file:
            for chunk in image_response.iter_content(1024):
                image_file.write(chunk)

    #     with open("/config/mydata.json", "r") as file:
    #         data = json.load(file)
        
    #     result = f"var1 is {data['var1']} and var2 is {data['var2']}"
    #     return result
    #     #return jsonify(data)
        
    # Render the image in the template (or you can directly return the image URL for simplicity)
    return render_template('index.html', image_path=url_for('static', filename=f'downloaded_image_{image_number}.jpg'))


if __name__ == '__main__':
    app.run(host='0.0.0.0')

