<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 6/8/17
  Time: 1:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<html>
<head>
    <title>Menu Père Noël</title>
    <sj:head/>
    <sb:head/>
</head>
<body>
    <script>
        $(document).ready(function()
        {
            $('.takeDecision').submit(function (e)
            {
                e.preventDefault();
                var data = $(this).serialize();

                $.ajax({
                    url: "takeDecision.action?" + data,
                    type: "POST",
                    contentType: "application/json: charset=utf-8",
                    dataType: "json",
                    success: function (result)
                    {
                        var validateDecisionForm =
                                '<h3>Choix</h3>' +
                                '<br/>' +
                                '<form id="validateDecision" name="validateDecision" class="validateDecision" ' +
                                    'action="/validateDecision.action" method="post">' +
                                    '<fieldset>' +
                                        '<input id="wise" name="choice" value="wise" type="radio" checked="checked"> Sage  </input><br/><br/>' +
                                        '<input id="notWise" name="choice" value="notWise" type="radio"> Pas sage </input><br/><br/>' +
                                        '<input id="goodQuestion" name="choice" value="goodQuestion" type="radio"> Ne pas se prononcer pour l\'instant </input><br/><br/>' +
                                        '<input id="validateDecisionSubmit" class="btn btn-primary" value="Valider" type="submit"> </input>' +
                                    '</fieldset>' +
                                '</form>';
                            $('#validateDecisionForm').html(validateDecisionForm);
                    },
                    error: function()
                    {
                        alert('Erreur!');
                    }
                });
            });
        });
    </script>
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-offset-4 col-md-4">
                <center><h1>Menu Père Noël</h1></center>
            </div>
            <div class="col-md-offset-2 col-md-2">
                <br/>
                <br/>
                <s:form action="menu" theme="bootstrap">
                    <s:hidden name="login" value="%{#session.login}"/>
                    <s:hidden name="password" value="%{#session.password}"/>
                    <s:submit cssClass="btn btn-primary" value="Retour"/>
                </s:form>
            </div>
        </div>
        <br/>
        <br/>
        <div class="row">
            <div class="col-md-offset-3 col-md-3">
                <h3>Prendre décision</h3>
                <br/>
                <s:form action="takeDecision" theme="bootstrap" cssClass="takeDecision">
                    <select id="kidSelected" name="kidSelected">
                        <s:iterator var="kid" value="%{session.kidsWithoutAnwser}">
                            <option value="<s:property value="%{#kid.identifiant}"/>"><s:property value="%{#kid.nom}"/> <s:property value="%{#kid.prenom}"/></option>
                        </s:iterator>
                    </select>
                    <br/>
                    <br/>
                    <s:submit cssClass="btn btn-primary" value="Sélectionner"/>
                </s:form>
                <br/>
                <br/>
                <div id="validateDecisionForm"></div>
            </div>
            <div class="col-md-offset-2 col-md-3">
                <h3>Enfants sages non livrés</h3>
                <br/>
                <s:form action="takeDecision" theme="bootstrap" cssClass="takeDecision">
                    <select id="kidSelected" name="kidSelected">
                        <s:iterator var="kidWise" value="%{session.kidsWithAnwser}">
                            <option value=""><s:property value="%{#kidWise.nom}"/> <s:property value="%{#kidWise.prenom}"/></option>
                        </s:iterator>
                    </select>
                </s:form>
            </div>
        </div>
    </div>
</body>
</html>