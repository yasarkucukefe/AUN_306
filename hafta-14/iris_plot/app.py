from flask import Flask, jsonify, request, send_from_directory
import plotly.express as px
import plotly.io as pio
import pandas as pd

app = Flask(__name__, static_folder="static")

df = px.data.iris().drop(columns=["species_id"], errors="ignore")

NUMERIC_COLS = ["sepal_length", "sepal_width", "petal_length", "petal_width"]
CATEGORICAL_COLS = ["species"]
PLOT_TYPES = ["scatter", "line", "bar", "histogram", "box", "violin"]


@app.route("/")
def index():
    return send_from_directory(app.static_folder, "index.html")


@app.route("/api/columns")
def columns():
    return jsonify({"numeric": NUMERIC_COLS, "categorical": CATEGORICAL_COLS})


@app.route("/api/plot_types")
def plot_types():
    return jsonify(PLOT_TYPES)


@app.route("/api/plot", methods=["POST"])
def plot():
    body = request.get_json(force=True, silent=True) or {}
    plot_type = body.get("plot_type")
    x = body.get("x")
    y = body.get("y")
    color = body.get("color") or None

    if plot_type not in PLOT_TYPES:
        return jsonify({"error": f"Unsupported chart type: {plot_type}"}), 400

    all_cols = NUMERIC_COLS + CATEGORICAL_COLS
    for col in [x, y, color]:
        if col is not None and col not in all_cols:
            return jsonify({"error": f"Unknown column: {col}"}), 400

    try:
        kwargs = {"data_frame": df, "color": color}
        if plot_type == "scatter":
            if not x or not y:
                return jsonify({"error": "scatter requires x and y"}), 400
            fig = px.scatter(x=x, y=y, **kwargs)
        elif plot_type == "line":
            if not x or not y:
                return jsonify({"error": "line requires x and y"}), 400
            fig = px.line(x=x, y=y, **kwargs)
        elif plot_type == "bar":
            if not x or not y:
                return jsonify({"error": "bar requires x and y"}), 400
            fig = px.bar(x=x, y=y, **kwargs)
        elif plot_type == "histogram":
            if not x:
                return jsonify({"error": "histogram requires x"}), 400
            fig = px.histogram(x=x, y=y if y else None, **kwargs)
        elif plot_type == "box":
            if not y:
                return jsonify({"error": "box requires y"}), 400
            fig = px.box(x=x if x else None, y=y, **kwargs)
        elif plot_type == "violin":
            if not y:
                return jsonify({"error": "violin requires y"}), 400
            fig = px.violin(x=x if x else None, y=y, **kwargs)
    except Exception as e:
        return jsonify({"error": str(e)}), 400

    return app.response_class(
        response=pio.to_json(fig),
        status=200,
        mimetype="application/json",
    )


if __name__ == "__main__":
    app.run(debug=True)
