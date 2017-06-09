<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 6/8/17
  Time: 12:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<html>
<head>
    <title>Menu</title>
    <sj:head/>
    <sb:head/>
</head>
<body>
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-offset-4 col-md-4">
                <center><h1>Accueil</h1></center>
            </div>
            <div class="col-md-offset-2 col-md-2">
                <br/>
                <br/>
                <s:form action="disconnection" theme="bootstrap">
                    <s:hidden name="accessKey" value="%{#session.accessKey}"/>
                    <s:submit cssClass="btn btn-primary" value="Déconnexion"/>
                </s:form>
            </div>
        </div>
        <br/>
        <br/>
        <s:iterator var="role" value="%{#session.roles}">
            <s:if test="%{#role == 'PERENOEL'}">
                <div class="row">
                    <div class="col-md-offset-4 col-md-4">
                        <s:form action="santaClaus" theme="bootstrap">
                            <center><s:submit cssClass="btn btn-primary" value="Père Noël" style="width: 200px"/></center>
                        </s:form>
                    </div>
                </div>
                <br/>
                <br/>
            </s:if>
            <s:if test="%{#role == 'BONNETVERT'}">
                <div class="row">
                    <div class="col-md-offset-4 col-md-4">
                        <s:form action="greenHat" theme="bootstrap">
                            <center><s:submit cssClass="btn btn-primary" value="Lutin vert" style="width: 200px"/></center>
                        </s:form>
                    </div>
                </div>
                <br/>
                <br/>
            </s:if>
            <s:if test="%{#role == 'BONNETROUGE'}">
                <div class="row">
                    <div class="col-md-offset-4 col-md-4">
                        <s:form action="redHat" theme="bootstrap">
                            <center><s:submit cssClass="btn btn-primary" value="Lutin rouge" style="width: 200px"/></center>
                        </s:form>
                    </div>
                </div>
                <br/>
                <br/>
            </s:if>
        </s:iterator>
    </div>
</body>
</html>
