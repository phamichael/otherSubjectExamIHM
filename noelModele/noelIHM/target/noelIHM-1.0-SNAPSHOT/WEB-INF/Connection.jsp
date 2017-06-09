<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 6/8/17
  Time: 12:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ page session="false" %>
<html>
<head>
    <title>Connection</title>
    <sj:head/>
    <sb:head/>
</head>
<body>
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-offset-4 col-md-4">
                <br/>
                <br/>
                <br/>
                <s:form action="menu" theme="bootstrap">
                    <s:textfield
                            id="login"
                            name="login"
                            placeholder="Login"/>
                    <s:textfield
                            id="password"
                            name="password"
                            placeholder="Mot de passe"/>
                    <s:submit id="btn-submit" cssClass="btn btn-primary pull-right" value="Connexion"/>
                </s:form>
            </div>
        </div>
    </div>
</body>
</html>
