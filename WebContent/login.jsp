<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

    <style>
        body {
    background: linear-gradient(to bottom, #3CB371, #228B22);
    font-family: Arial, sans-serif;
    text-align: center;
        margin: 0; 
    height: 100vh;
        }

        h1 {
            line-height: 1;
            color: #ffffff;
        }

        button {
            background-color: #21689C;
            color: #fff;
            padding: 10px 20px;
            font-size: 16px;
            border: none;
            cursor: pointer;
            background-color: darkolivegreen;
            border-radius: 30px;
        }
        
              input,
      button {
        display: block;
        margin: 0 auto;
		font-size: 20px;
        color: white;
        background-color: darkolivegreen;
        border-radius: 30px;
      }

        label {
            display: block;
            text-align: center;
            color: white;
            margin-bottom: 10px;
        }

        input[type="text"], input[type="password"] {
            padding: 8px;
            margin: 5px 0;
            box-sizing: border-box;
            width: 100%; 
        }

        form {
            display: inline-block;
        }
        
        input:hover {
            background-color: #006400;
            color: #ffffff;
        }
    </style>
    
    <meta charset="UTF-8">
    <title>Sistema de Presenças</title>
</head>
<body>

    <h1>Sistema de Presenças</h1>

    <form action="login" method="post">
        <label for="login">Login:</label>
        <input type="text" id="login" name="login" required>
        <br>

        <label for="senha">Senha:</label>
        <input type="password" id="senha" name="senha" required>
        <br>

        <input type="submit" value="Login">
    </form>

</body>
</html>