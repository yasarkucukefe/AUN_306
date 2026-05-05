# Todo App — Flask + SQLite3 + Bootstrap

## Overview

A web application that allows users to create, view, update, and delete tasks (CRUD). Built with Flask, backed by SQLite3, and styled with Bootstrap 5.

---

## Tech Stack

| Layer     | Technology          |
|-----------|---------------------|
| Backend   | Python 3, Flask     |
| Database  | SQLite3             |
| Frontend  | Jinja2 + Bootstrap 5|

---

## Project Structure

```
todoapp/
├── app.py               # Flask application entry point
├── database.py          # Database initialization and helper functions
├── schema.sql           # SQL schema for tasks table
├── templates/
│   ├── base.html        # Base layout with Bootstrap navbar
│   ├── index.html       # Task list page
│   ├── create.html      # Create task form
│   └── edit.html        # Edit task form
├── static/
│   └── style.css        # Optional custom CSS overrides
└── .spec/
    └── CLAUDE.md        # This file
```

---

## Database Schema

```sql
CREATE TABLE IF NOT EXISTS tasks (
    id        INTEGER PRIMARY KEY AUTOINCREMENT,
    title     TEXT    NOT NULL,
    description TEXT,
    done      INTEGER NOT NULL DEFAULT 0,   -- 0 = pending, 1 = completed
    created_at TEXT   NOT NULL DEFAULT (datetime('now'))
);
```

---

## Routes

| Method | URL             | Description                     |
|--------|-----------------|---------------------------------|
| GET    | `/`             | List all tasks                  |
| GET    | `/create`       | Show create task form           |
| POST   | `/create`       | Submit new task                 |
| GET    | `/edit/<id>`    | Show edit form for a task       |
| POST   | `/edit/<id>`    | Submit updated task             |
| POST   | `/delete/<id>`  | Delete a task                   |
| POST   | `/toggle/<id>`  | Toggle done/pending status      |

---

## Features

### Task List (`/`)
- Display all tasks in a Bootstrap table or card list.
- Each row shows: title, description (truncated), status badge (Done / Pending), created date.
- Action buttons per row: Edit, Delete, Toggle status.
- Empty state message when no tasks exist.

### Create Task (`/create`)
- Form fields: **Title** (required), **Description** (optional).
- Submit button creates the task and redirects to `/`.
- Validation: title must not be empty; show Bootstrap alert on error.

### Edit Task (`/edit/<id>`)
- Pre-populated form with existing title and description.
- Submit updates the task and redirects to `/`.
- Cancel link returns to `/`.

### Delete Task (`/delete/<id>`)
- POST request only (triggered by a form button with confirmation).
- Deletes the task and redirects to `/`.

### Toggle Status (`/toggle/<id>`)
- Flips `done` between 0 and 1.
- Redirects back to `/`.

---

## UI / Bootstrap Guidelines

- Use **Bootstrap 5** via CDN (no local install needed).
- Base layout (`base.html`) includes: navbar with app name, main content block, Bootstrap JS bundle CDN link.
- Use Bootstrap `table table-hover` or `list-group` for task display.
- Use `badge bg-success` for Done, `badge bg-secondary` for Pending.
- Use `btn btn-sm` variants for action buttons (primary = Edit, danger = Delete, outline-secondary = Toggle).
- Flash messages displayed with Bootstrap `alert alert-*` components.

---

## Implementation Notes

- Use `flask.g` and `sqlite3.connect` for per-request database connections, closed with `teardown_appcontext`.
- Use `app.secret_key` for `flash()` messages.
- All DELETE and UPDATE operations must look up the task by `id` first; return 404 if not found.
- Use `redirect(url_for('index'))` after any write operation.
- Boolean `done` stored as INTEGER (0/1) in SQLite; convert to Python bool in the view layer.
- Initialize the database automatically on first run via `database.init_db()` called inside `if __name__ == '__main__'`.

---

## Running the App

```bash
python app.py
```

App runs on `http://127.0.0.1:5000` in debug mode by default.

---

## Out of Scope

- User authentication / login
- Task priorities or due dates
- REST API / JSON endpoints
- Pagination
