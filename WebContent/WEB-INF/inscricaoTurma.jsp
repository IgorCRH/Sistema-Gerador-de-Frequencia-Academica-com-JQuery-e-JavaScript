<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="sistemarodrigo.DisciplinaModel" %>
<%@ page import="sistemarodrigo.DisciplinaDAO" %>
<%@ page import="sistemarodrigo.TurmaModel" %>
<%@ page import="sistemarodrigo.TurmaDAO" %>
<%@ page import="sistemarodrigo.AlunoDAO" %>
<%@ page import="java.util.List" %>

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
        
        select {
            padding: 8px;
            margin: 5px 0;
            box-sizing: border-box;
            width: 100%; 
            background-color: darkolivegreen;
            color: white;
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
        
        input, button:hover {
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
        $("#labelMatricula, #matricula").addClass("oculto");
        
        $("#turmaesc").change(function() {
            if ($(this).val() !== "") {
                $("#labelMatricula, #matricula").removeClass("oculto");
            } else {
                $("#labelMatricula, #matricula").addClass("oculto");
            }
        });
    });
</script>

    <meta charset="UTF-8">
    <title>Inscrição em Turma</title>
</head>
<body>

<%
    List<DisciplinaModel> disciplinas = DisciplinaDAO.getAllDisciplinas();
    request.setAttribute("disciplinas", disciplinas);

    String disciplinaesc = request.getParameter("disciplinaesc");
    session.setAttribute("disciplinaesc", disciplinaesc);
    
    List <TurmaModel> turmasdisc = TurmaDAO.getTurmasPorDisciplina(disciplinaesc);
    session.setAttribute("turmasdisc", turmasdisc);
    
    String turmaescolhida = request.getParameter("turmaesc");
    session.setAttribute("turmaesc", turmaescolhida);
%>

<h1>Inscrição em Disciplina</h1>

<form action="inscricaoTurma.jsp" class="disciplina-form" method="post">
    <label for="disciplinaesc">Disciplina:</label>
    <select id="disciplinaesc" name="disciplinaesc">
        <option value="">Selecione uma disciplina</option>
        <c:forEach var="disciplina" items="${disciplinas}">
            <option value="${disciplina.codigoDisciplina}" ${disciplina.codigoDisciplina eq disciplinaesc ? 'selected' : ''}>${disciplina.nomeDisciplina}</option>
        </c:forEach>
    </select>
    <input type="submit" value="Confirmar a Disciplina">
    </form>
<br>

<div class="center-container">
<form action="inscricaoTurma.jsp" id="turmamatForm" method="post">
    <label for="turmaesc" id="labelturmaesc">Turma:</label>
    <select id="turmaesc" name="turmaesc">
        <option value="">Selecione uma turma</option>
        <c:forEach var="turma" items="${turmasdisc}">
            <option value="${turma.numeroTurma}">${turma.numeroTurma}</option>
        </c:forEach>
    </select>
   <br>

    <label for="matricula" id="labelMatricula">Matrícula do Aluno:</label>
    <input type="text" name="matricula" id="matricula">
    <br>
        <input type="hidden" name="disciplinaesc" value="${disciplinaesc}">
        <input type="submit" value="Inscrever na Turma">
</form>
</div>

<button id="button1" onclick="window.location.href='paginadoaluno.jsp'">Voltar para Página do Aluno</button>

<%    
    String disciplinainscricao = (String) session.getAttribute("disciplinaesc");
    String turmainscricao = (String) session.getAttribute("turmaesc");
    String matricula = request.getParameter("matricula");

    AlunoDAO.cadastrarAlunonaTurma(turmainscricao, matricula, disciplinainscricao);
%>


</body>
</html>
