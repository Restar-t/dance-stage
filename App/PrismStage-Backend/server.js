const express = require('express');
const app = express();
const port = 3000; // 服务器运行的端口

// 这个中间件让我们的服务器能够解析JSON格式的请求体
app.use(express.json());

// --- 临时的、假的数据库 ---
const users = [
    { id: 1, username: 'test', password: '123' } // 真实项目中，密码绝不能这样明文存储！
];

// --- API 端点 (Endpoints) ---

// 1. 登录API
app.post('/api/login', (req, res) => {
    const { username, password } = req.body; // 获取App发来的用户名和密码

    console.log(`收到登录请求: 用户名=${username}, 密码=${password}`);

    const user = users.find(u => u.username === username && u.password === password);

    if (user) {
        // 登录成功
        res.json({ 
            success: true, 
            message: '登录成功!',
            token: 'a_fake_but_valid_looking_token_for_testing' // 真实项目中会生成一个JWT Token
        });
    } else {
        // 登录失败
        res.status(401).json({ success: false, message: '用户名或密码错误' });
    }
});

// 2. 注册API (简化版)
app.post('/api/register', (req, res) => {
    const { username, password } = req.body;
    
    console.log(`收到注册请求: 用户名=${username}`);

    if (users.find(u => u.username === username)) {
        return res.status(400).json({ success: false, message: '用户名已存在' });
    }

    const newUser = { id: users.length + 1, username, password };
    users.push(newUser);
    console.log('当前用户列表:', users);

    res.json({ 
        success: true, 
        message: '注册成功!',
        token: 'a_new_fake_token_for_the_new_user'
    });
});


// 启动服务器
app.listen(port, () => {
    console.log(`棱镜舞台后端服务器正在 http://localhost:${port} 上运行`);
});