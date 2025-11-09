bash
pip install sqlite3
import sqlite3

class SQLiteDAO:
    def __init__(self, db_name):
        self.conn = sqlite3.connect(db_name)
        self.conn.row_factory = sqlite3.Row  # This allows us to access columns by name
        self.cursor = self.conn.cursor()

    def select_all_from_table(self, table_name):
        query = f"SELECT * FROM {table_name}"
        self.cursor.execute(query)
        rows = self.cursor.fetchall()
        return [dict(row) for row in rows]  # Convert rows to dicts for easier access

    def close(self):
        self.conn.close()

# Example usage:
if __name__ == "__main__":
    db_name = 'example.db'  # Replace with your database file name or path
    dao = SQLiteDAO(db_name)
    
    table_name = input("Enter the table name: ")
    results = dao.select_all_from_table(table_name)
    
    for row in results:
        print(row)
    
    dao.close()