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
            transition: background 0.5s ease; /* Adiciona uma transição suave */
        }
        
        button:hover {
            background: linear-gradient(to bottom, #ffffff, #808080); 
            color: #006400;
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
        
        p {
            color: #ffffff; /* Substitua pelo código de cor desejado */
        }

        form {
            display: inline-block;
        }
        
        input:hover {
            background-color: #006400;
            color: #ffffff;
        }
        
        .destacado {
            background-color: white;
            color: #006400;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
            width: 100%;
        }
    </style>
    
    <meta charset="UTF-8">
    <title>Página do Aluno</title>
</head>
<body>

    <h1>Bem-vindo, Docente!</h1>


    <button id="button1" onclick="window.location.href='Frequencia.jsp'">Administrar Frequencia</button>

<form action="LogoutServlet" method="post">
    <button type="submit">Sair</button>
</form>


</body>
</html>
