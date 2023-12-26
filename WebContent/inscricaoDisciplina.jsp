<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="sistemarodrigo.DisciplinaDAO" %>

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
        
        p {
            color: #ffffff; /* Substitua pelo código de cor desejado */
        }

        form {
            display: inline-block;
        }
        
        button:hover {
            background: linear-gradient(to bottom, #ffffff, #808080);
            color: #006400;
        }
        
        input[type="submit"]:hover {
            background: linear-gradient(to bottom, #ffffff, #808080);
            color: #006400;
        }

       
       .oculto {
       display: none;
       }
       
    </style>
    
        <script type="text/javascript" src="<c:url value='jquery-3.7.1.min.js'/>"></script>
    
<script type="text/javascript">
    $(document).ready(function() {
        $("#labelDep, #departamentodisc").addClass("oculto");
        
        $("#nomedisc").change(function() {
            if ($(this).val() !== "") {
                $("#labelDep, #departamentodisc").removeClass("oculto");
            } else {
                $("#labelDep, #departamentodisc").addClass("oculto");
            }
        });
    });
</script>

    <meta charset="UTF-8">
    <title>Cadastro - Disciplina</title>
</head>
<body>

<h1>Cadastro - Disciplina</h1>

<form action="CadastrarDisciplinaServlet" method="post">
    <label for="nome" id="labelDisc">Nome da Disciplina:</label>
    <input type="text" name="nomedisc" id="nomedisc" required>
    <br>

    <label for="departamento" id="labelDep">Departamento:</label>
    <input type="text" name="departamentodisc" id="departamentodisc" required>
    <br>

    <input type="submit" value="Inscrição da Disciplina">
</form>
<br>
<br>
<button id="button1" onclick="window.location.href='paginacoordenador.jsp'">Voltar para Página do Coordenador</button>

</body>
</html>
