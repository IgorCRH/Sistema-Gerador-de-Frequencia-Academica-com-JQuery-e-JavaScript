<%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="sistemarodrigo.DisciplinaModel" %>
<%@ page import="sistemarodrigo.DisciplinaDAO" %>
<%@ page import="sistemarodrigo.TurmaModel" %>
<%@ page import="sistemarodrigo.TurmaDAO" %>
<%@ page import="sistemarodrigo.AlunoDAO" %>
<%@ page import="sistemarodrigo.FrequenciaChamadaDAO" %>
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

    <meta charset="UTF-8">
    <title>Área de Frequência</title>
    <script>
        function submitForm() {
            document.forms["discForm"].submit();
        }
    </script>

    
<script type="text/javascript">
    $(document).ready(function() {
        $("#labelmatricula, #labelnumeroAula, #labelpresenca, #matriculaaula, #numaula, #presencaaula").addClass("oculto");
        
        $("#turma").click(function() {
            if ($(this).val() !== "") {
            	$("#labelmatricula, #labelnumeroAula, #labelpresenca, #matriculaaula, #numaula, #presencaaula").removeClass("oculto");
            } else {
            	$("#labelmatricula, #labelnumeroAula, #labelpresenca, #matriculaaula, #numaula, #presencaaula").addClass("oculto");
            }
        });
    });
</script>

</head>
<body>

<%
    List<DisciplinaModel> disciplinas = DisciplinaDAO.getAllDisciplinas();
    request.setAttribute("disciplinas", disciplinas);

    String disciplinaesc = request.getParameter("disciplinaesc");
    session.setAttribute("disciplinaesc", disciplinaesc);
    
    List<TurmaModel> turmasdisc = TurmaDAO.getTurmasPorDisciplina(disciplinaesc);
    session.setAttribute("turmasdisc", turmasdisc);
    
    String turmaesc = request.getParameter("turmaesc");
    session.setAttribute("turmaesc", turmaesc);
    
    String matricula = request.getParameter("matricula");
    session.setAttribute("matricula", matricula);
   
%>

<h1>Área de Frequência das Aulas</h1>

<form id="discForm" action="Frequencia.jsp" method="post" onsubmit="submitForm()">
    <label for="disciplina">Disciplina:</label>
    <select name="disciplinaesc" id="disciplina" onchange="submitForm()">
        <c:forEach var="disciplina" items="${disciplinas}">
            <option value="${disciplina.codigoDisciplina}" ${disciplina.codigoDisciplina eq disciplinaesc ? 'selected' : ''}>${disciplina.nomeDisciplina}</option>
        </c:forEach>
    </select>
    <button type="submit" name="enviadisc" value="enviadisc">Determinar Disciplina</button> 

    <label for="turma">Turma:</label>
    <select name="turmaesc" id="turma" onchange="submitForm()">
        <c:forEach var="turma" items="${turmasdisc}">
            <option value="${turma.numeroTurma}">${turma.numeroTurma}</option>
        </c:forEach>
    </select>

    <label for="labelmatricula" id="labelmatricula">Matrícula do Aluno:</label>
    <input type="text" name="matricula" id="matriculaaula" required>
    <br>

    <label for="labelnumeroAula" id="labelnumeroAula">Número da Aula:</label>
    <input type="text" name="numeroAula" id="numaula" required>
    <br>

    <label for="labelpresenca" id="labelpresenca">Presença:</label>
    <input type="checkbox" name="presenca" id="presencaaula" value="1">
    <br>

  <button type="submit" name="enviafrequencia" value="enviafrequencia">Enviar Frequência</button>  
</form>

<% 

if ("enviafrequencia".equals(request.getParameter("enviafrequencia"))) {
    String matriculaFreq = request.getParameter("matricula");
    String disciplinaFreq = (String) session.getAttribute("disciplinaesc");
    String turmaFreq = (String) session.getAttribute("turmaesc");
    String numeroAula = request.getParameter("numeroAula");
    String presenca = request.getParameter("presenca");
    boolean presencaBoolean = "1".equals(presenca);
    FrequenciaChamadaDAO.inserirFrequenciaChamada(matriculaFreq, disciplinaFreq, turmaFreq, numeroAula, presencaBoolean);
}
%>



<br>
<form action="paginadocente.jsp">
    <button type="submit">Ir para página do docente</button>
</form>

</body>
</html>
