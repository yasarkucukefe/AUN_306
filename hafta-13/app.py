from flask import Flask, json, render_template, request, jsonify
import plotly.express as px
import pandas as pd

app = Flask(__name__)
df = px.data.iris() # Iris dataset

@app.route('/')
def index():
    return render_template('index.html')

@app.route('/get_column_names', methods=['GET'])
def columns():
    cols = df.columns
    return jsonify(cols.tolist())


@app.route('/plot', methods=['POST'])
def plot():
    data = request.get_json()

    x = data["x"]
    y = data["y"]
    chart = data["chart"]

    if chart == "scatter":
        print("scatter")
        print(x, y)
        print(df[x].dtype, df[y].dtype)
        fig = px.scatter(df, x=x, y=y, color="species")

    elif chart == "line":
        fig = px.line(df, x=x, y=y)

    elif chart == "bar":
        fig = px.bar(df, x=x, y=y, color="species")

    elif chart == "box":
        fig = px.box(df, x=x, y=y, color="species")

    elif chart == "histogram":
        fig = px.histogram(df, x=x, color="species")

    fig.update_layout(
        margin=dict(l=40, r=20, t=40, b=40),
        paper_bgcolor="white",
        plot_bgcolor="#f8f9fa",
    )

    fig.write_html("debug_plot.html")

    return jsonify({"figure": json.loads(fig.to_json())})


if __name__ == '__main__':
    app.run(debug=True)