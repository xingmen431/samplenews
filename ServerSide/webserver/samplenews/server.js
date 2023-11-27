const express = require('express');
const mysql = require('mysql');
const bodyParser = require('body-parser');
const cors = require('cors');

const app = express();

app.use(cors());
app.use(bodyParser.json());

const db = mysql.createConnection({
    host: '127.0.0.1',
    user: 'root',  // 数据库用户名
    password: '',  // 数据库密码
    database: 'samplenews' // 数据库名
});

db.connect(err => {
    if (err) throw err;
    console.log('MySQL connected');
});

// 获取所有数据
app.get('/api/items', (req, res) => {
    const sql = 'SELECT * FROM sn_news';
    db.query(sql, (err, results) => {
        if (err) throw err;
        res.json(results);
    });
});

// 创建数据
app.post('/api/items', (req, res) => {
    const data = req.body;
    const sql = 'INSERT INTO sn_news SET ?';
    db.query(sql, data, (err, result) => {
        if (err) throw err;
        res.json({ message: 'Data added', id: result.insertId });
    });
});

// 更新数据
app.put('/api/items/:id', (req, res) => {
    const data = req.body;
    const sql = 'UPDATE sn_news SET ? WHERE id = ?';
    db.query(sql, [data, req.params.id], (err, result) => {
        if (err) throw err;
        res.json({ message: 'Data updated' });
    });
});

// 删除数据
app.delete('/api/items/:id', (req, res) => {
    const sql = 'DELETE FROM sn_news WHERE id = ?';
    db.query(sql, [req.params.id], (err, result) => {
        if (err) throw err;
        res.json({ message: 'Data deleted' });
    });
});

const PORT = process.env.PORT || 3000;
app.listen(PORT, () => console.log(`Server running on port ${PORT}`));