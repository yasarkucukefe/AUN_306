import sqlite3
import os
from flask import g

DATABASE = os.path.join(os.path.dirname(__file__), 'tasks.db')


def get_db():
    if 'db' not in g:
        g.db = sqlite3.connect(DATABASE)
        g.db.row_factory = sqlite3.Row
    return g.db


def close_db(e=None):
    db = g.pop('db', None)
    if db is not None:
        db.close()


def init_db():
    db = sqlite3.connect(DATABASE)
    schema = os.path.join(os.path.dirname(__file__), 'schema.sql')
    with open(schema) as f:
        db.executescript(f.read())
    db.commit()
    db.close()


def init_app(app):
    app.teardown_appcontext(close_db)
