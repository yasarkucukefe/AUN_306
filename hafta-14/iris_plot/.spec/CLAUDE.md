# Iris Plot Web Application — Spec

## Overview

A Flask web application that lets users explore the Iris dataset interactively. Users choose which columns to plot and which chart type to use. Plots are rendered in the browser via Plotly. There is no Jinja2 templating — the frontend is served as a static HTML file, and all data exchange happens through JSON API endpoints.

---

## Tech Stack

| Layer     | Technology                          |
|-----------|-------------------------------------|
| Backend   | Python 3, Flask                     |
| Dataset   | Plotly `plotly.express.data.iris()` |
| Plotting  | Plotly (Python `plotly` library)    |
| Frontend  | Vanilla HTML + JS (no Jinja2)       |
| API       | Flask JSON endpoints (`/api/*`)     |

---

## Project Structure

```
iris_plot/
├── app.py                  # Flask application entry point
├── requirements.txt
└── static/
    └── index.html          # Single-page frontend (no Jinja2)
```

---

## Backend (`app.py`)

### Startup

- Load the Iris dataset once at startup using `plotly.express.data.iris()`.
- Store it as a `pandas.DataFrame`. `px.data.iris()` returns columns: `sepal_length`, `sepal_width`, `petal_length`, `petal_width`, `species`, `species_id` — drop `species_id` if not needed.

### Routes

#### `GET /`
- Serve `static/index.html` using `flask.send_from_directory` or `app.send_static_file`.
- **No** `render_template`. No Jinja2 at all.

#### `GET /api/columns`
- Return a JSON object:
  ```json
  {
    "numeric": ["sepal_length", "sepal_width", "petal_length", "petal_width"],
    "categorical": ["species"]
  }
  ```

#### `GET /api/plot_types`
- Return supported chart types:
  ```json
  ["scatter", "line", "bar", "histogram", "box", "violin"]
  ```

#### `POST /api/plot`
- Request body (JSON):
  ```json
  {
    "plot_type": "scatter",
    "x": "sepal_length",
    "y": "petal_length",
    "color": "species"       // optional, for grouping/coloring
  }
  ```
- Build a Plotly figure object using `plotly.express` or `plotly.graph_objects`.
- Return the figure as JSON using `plotly.io.to_json(fig)` so the frontend can render it with `Plotly.react`.
- Return HTTP 400 with `{"error": "..."}` for invalid input (unknown column, unsupported chart type, etc.).

### Chart-type mapping

| `plot_type`  | Plotly function              | Required fields      |
|--------------|------------------------------|----------------------|
| `scatter`    | `px.scatter`                 | `x`, `y`            |
| `line`       | `px.line`                    | `x`, `y`            |
| `bar`        | `px.bar`                     | `x`, `y`            |
| `histogram`  | `px.histogram`               | `x` (y is optional) |
| `box`        | `px.box`                     | `y`                 |
| `violin`     | `px.violin`                  | `y`                 |

---

## Frontend (`static/index.html`)

A single self-contained HTML file. No external templating engine. Uses:
- The Plotly CDN script (`plotly.min.js`).
- Vanilla JavaScript (`fetch` API) for all server communication.

### Layout

```
┌────────────────────────────────────────────┐
│  Iris Dataset Explorer                     │
├──────────────┬─────────────────────────────┤
│ Controls     │  Plot Area                  │
│              │                             │
│ X Axis: [v]  │  <div id="plotly-chart">    │
│ Y Axis: [v]  │                             │
│ Color:  [v]  │                             │
│ Type:   [v]  │                             │
│              │                             │
│ [Plot]       │                             │
└──────────────┴─────────────────────────────┘
```

### Behavior

1. On page load, `fetch('/api/columns')` and `fetch('/api/plot_types')` to populate the dropdowns.
2. "X Axis" and "Y Axis" dropdowns list all numeric columns. "Color" dropdown adds an "None" option plus categorical columns.
3. "Chart Type" dropdown is populated from `/api/plot_types`.
4. Clicking **Plot** sends `POST /api/plot` with the selected values.
5. On success, render the figure with `Plotly.react('plotly-chart', data, layout)` (parse the JSON response first).
6. On error, display the `error` field from the response in a visible message area below the controls.

---

## `requirements.txt`

```
flask
pandas
plotly
```

---

## Constraints & Rules

- **No Jinja2** — do not use `render_template`, `{{ }}` expressions, or template filters anywhere.
- **No external frontend frameworks** (React, Vue, etc.) — plain HTML/JS only.
- **Plotly figure JSON** must be produced server-side and consumed by the Plotly JS library client-side.
- All routes under `/api/` must return `application/json`.
- The root route `/` must return the static HTML file.
- Input validation on the `/api/plot` endpoint: reject unknown column names and unsupported chart types.

---

## Out of Scope

- Authentication or user sessions.
- Saving/exporting plots.
- Editing the dataset.
- Any database.
