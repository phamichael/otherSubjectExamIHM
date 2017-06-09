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
    <title>Menu lutin vert</title>
    <sj:head/>
    <sb:head/>
</head>
<body>
    <script>
        $(document).ready(function()
        {
            $('.addKid').hide();
            $('.search').submit(function(e)
            {
                e.preventDefault();
                var data = $(this).serialize();

                $.ajax({
                    url: "search.action?" + data,
                    type: "POST",
                    contentType: "application/json: charset=utf-8",
                    dataType: "json",
                    success: function(result)
                    {
                        if(result.kids.length > 0)
                        {
                            var formSelectKid =
                                    '<h3>Résultats</h3>' +
                                    '<br/>' +
                                    '<form id="selectKid" name="selectKid" class="selectKid" ' +
                                    'action="/selectKid.action" method="post">' +
                                        '<fieldset>' +
                                            '<select id="id" name="id">';
                            var cpt = 0;
                            for (var kid in result.kids) {
                                formSelectKid +=
                                                '<option value="' + result.kids[cpt].identifiant + '">' + result.kids[cpt].nom + ' ' + result.kids[cpt].prenom + '</option>';
                                cpt++;
                            }
                            formSelectKid +=
                                            '</select><br/><br/>' +
                                            '<input id="selectKidSubmit" class="btn btn-primary" value="Sélectionner" type="submit"> </input>' +
                                        '</fieldset>' +
                                    '</form>';
                            $('#formSelectKid').html(formSelectKid);
                            $('.addKid').hide();
                        } else
                        {
                            $('#formSelectKid').html('<h3>Résultats</h3><br/>'
                                    + '<strong>Aucun résultat.</strong><br/><br/>');//+
                                    //'<a href="/registrerKid.action?">Ajouter cette enfant</a>');
                            $('.addKid').show();
                        }
                    },
                    error: function()
                    {
                        alert('Erreur!');
                    }
                });
            });

            $('.selectKid').submit(function(e)
            {
                e.preventDefault();
                var data = $(this).serialize();

                $.ajax({
                    url: "selectKid.action?" + data,
                    type: "POST",
                    contentType: "application/json: charset=utf-8",
                    dataType: "json",
                    success: function(result)
                    {
                        var formEditGifts =
                                    '<form id="editGift" class="editGift" name="editGift" ' +
                                    'action="/editGift.action" method="post">' +
                                        '<fieldset>' +
                                            'Nom: ' + result.kid.nom + '</br>' +
                                            'Prénom: ' + result.kid.prenom + '</br>' +
                                            'Adresse: ' + result.kid.adresse + '</br>' +
                                            'Ville: ' + result.kid.ville + '</br>' +
                                            'Code postal: ' + result.kid.codePostal + '</br>' +
                                            'Cadeaux: <br/>' +
                                            '<select id="giftsSelected"  name="giftSelected">';
                        var cpt = 0;
                        for(var gift in result.gifts)
                        {
                            formEditGifts +=
                                                '<option id="' + result.gifts[cpt].libelle + '" name="' + result.gifts[cpt].libelle + '">' + result.gifts[cpt].libelle + '> </option>';
                        }

                        formEditGifts +=
                                            '</select><br/><br/>' +
                                            '<input id="editGiftsSubmit" class="btn btn-primary" value="Enregistrer modification" type="submit"> </input>' +
                                        '</fieldset>' +
                                    '</form>';
                        $('#formEditGifts').html(formEditGifts);
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
                <center><h1>Menu lutin vert</h1></center>
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
            <div class="col-md-offset-4 col-md-4">
                <h3>Faire une recherche</h3>
                <br/>
                <s:form action="search" theme="bootstrap" cssClass="search">
                    <s:textfield
                            id="name"
                            name="name"
                            placeholder="Nom"/>
                    <s:submit cssClass="btn btn-primary" value="Rechercher"/>
                </s:form>
                <br/>
                <div id="formSelectKid"></div>
                <div id="addKid" class="addKid">
                    <s:url action="registerKid" var="addKid">
                    </s:url>
                    <s:a href="%{addKid}">
                        Ajouter cette enfant
                    </s:a>
                </div>
                <br/>
                <br/>
                <div id="formEditGifts"></div>
            </div>
        </div>
    </div>
</body>
</html>
