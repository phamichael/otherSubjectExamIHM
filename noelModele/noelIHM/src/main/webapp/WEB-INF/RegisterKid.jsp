<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 6/8/17
  Time: 3:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<html>
<head>
    <title>Title</title>
    <sj:head/>
    <sb:head/>
</head>
<body>
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-offset-4 col-md-4">
                <center><h1>Ajouter un nouvel enfant</h1></center>
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
        <div class="row">
            <div class="col-md-offset-4 col-md-4">
                <br/>
                <br/>
                <s:form action="addKid" theme="bootstrap">
                    <s:textfield
                            id="name"
                            name="name"
                            placeholder="Nom"/>
                    <s:textfield
                            id="firstName"
                            name="firstName"
                            placeholder="Prénom"/>
                    <s:textfield
                            id="address"
                            name="address"
                            placeholder="Adresse"/>
                    <s:textfield
                            id="zipCode"
                            name="zipCode"
                            placeholder="Code postal"/>
                    <s:textfield
                            id="city"
                            name="city"
                            placeholder="Ville"/>
                    <s:textfield
                            id="day"
                            name="day"
                            placeholder="Jour de naissance"/>/
                    <s:textfield
                            id="month"
                            name="month"
                            placeholder="Mois"/>/
                    <s:textfield
                            id="year"
                            name="year"
                            placeholder="Année"/>
                    Cadeaux demandés:
                    <br/>
                    <br/>
                    <select id="toy1" name="toy1">
                        <s:iterator var="toy" value="%{session.toys}">
                            <option value="<s:property value="%{#toy.libelle}"/>"><s:property value="%{#toy.libelle}"/></option>
                        </s:iterator>
                    </select>
                    <br/>
                    <br/>
                    <select id="toy2" name="toy2">
                        <s:iterator var="toy" value="%{session.toys}">
                            <option value="<s:property value="%{#toy.libelle}"/>"><s:property value="%{#toy.libelle}"/></option>
                        </s:iterator>
                    </select>
                    <br/>
                    <br/>
                    <select id="toy3" name="toy3">
                        <s:iterator var="toy" value="%{session.toys}">
                            <option value="<s:property value="%{#toy.libelle}"/>"><s:property value="%{#toy.libelle}"/></option>
                        </s:iterator>
                    </select>
                    <br/>
                    <br/>
                    <select id="toy4" name="toy4">
                        <s:iterator var="toy" value="%{session.toys}">
                            <option value="<s:property value="%{#toy.libelle}"/>"><s:property value="%{#toy.libelle}"/></option>
                        </s:iterator>
                    </select>
                    <br/>
                    <br/>
                    <select id="toy5" name="toy5">
                        <s:iterator var="toy" value="%{session.toys}">
                            <option value="<s:property value="%{#toy.libelle}"/>"><s:property value="%{#toy.libelle}"/></option>
                        </s:iterator>
                    </select>
                    <br/>
                    <br/>
                    <s:submit id="btn-submit" cssClass="btn btn-primary pull-right" value="Ajouter"/>
                </s:form>

            </div>
        </div>
    </div>
</body>
</html>
