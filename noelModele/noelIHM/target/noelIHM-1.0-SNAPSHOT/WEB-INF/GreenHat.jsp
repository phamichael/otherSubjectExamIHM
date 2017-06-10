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
                            var selectKidForm =
                                    '<h3>Résultats</h3>' +
                                    '<br/>' +
                                    '<form id="selectKid" name="selectKid" class="selectKid" ' +
                                    'action="/selectKid.action" method="post">' +
                                        '<fieldset>' +
                                            '<select id="kidSelected" name="kidSelected">';
                            var cpt = 0;
                            for (var kid in result.kids) {
                                selectKidForm +=
                                                '<option value="' + result.kids[cpt].identifiant + '">' + result.kids[cpt].nom + ' ' + result.kids[cpt].prenom + '</option>';
                                cpt++;
                            }
                            selectKidForm +=
                                            '</select><br/><br/>' +
                                            '<input id="selectKidSubmit" class="btn btn-primary" value="Sélectionner" type="submit"> </input>' +
                                        '</fieldset>' +
                                    '</form>';
                            $('#selectKidForm').html(selectKidForm);
                            $('.addKid').hide();
                        } else
                        {
                            $('#selectKidForm').html('<h3>Résultats</h3><br/>'
                                    + '<strong>Aucun résultat.</strong><br/><br/>');//+
                                    //'<a href="/registrerKid.action?">Ajouter cette enfant</a>');
                            $('.addKid').show();
                        }
                        $('#updateGiftsForm').html('');
                    },
                    error: function()
                    {
                        alert('Erreur!');
                    }
                });
            });

            $('body').on('submit', '.selectKid', function(e)
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
                        var updateGiftsForm =
                                    '<h3>Modifier la liste des cadeaux</h3>' +
                                    '<br/>' +
                                    '<form id="updateGifts" class="updateGifts" name="updateGifts" ' +
                                    'action="/updateGifts.action" method="post">' +
                                        '<fieldset>' +
                                            'Nom: ' + result.kid.nom + '</br></br>' +
                                            'Prénom: ' + result.kid.prenom + '</br></br>' +
                                            'Adresse: ' + result.kid.adresse + '</br></br>' +
                                            'Ville: ' + result.kid.ville + '</br></br>' +
                                            'Code postal: ' + result.kid.codePostal + '</br></br>' +
                                            'Cadeaux: <br/></br>' +
                                            '<select id="giftSelected"  name="giftSelected">';
                        var cpt = 0;
                        for(var gift in result.gifts)
                        {
                            updateGiftsForm +=
                                                '<option id="' + result.gifts[cpt].libelle + '" name="' + result.gifts[cpt].libelle + '">' + result.gifts[cpt].libelle + '</option>';
                            cpt++;
                        }

                        updateGiftsForm +=
                                            '</select><br/><br/>' +
                                            'Nouvelle liste de cadeaux: <br/></br>' +
                                            '<select id="toy1"  name="toy1">';

                        cpt = 0;
                        for(var toy1 in result.toys)
                        {
                            updateGiftsForm +=
                                                '<option id="' + result.toys[cpt].libelle + '" name="' + result.toys[cpt].libelle + '">' + result.toys[cpt].libelle + '</option>';
                            cpt++;
                        }
                        updateGiftsForm +=
                                            '</select><br/><br/>' +
                                            '<select id="toy2"  name="toy2">';

                        cpt = 0;
                        for(var toy2 in result.toys)
                        {
                            updateGiftsForm +=
                                                '<option id="' + result.toys[cpt].libelle + '" name="' + result.toys[cpt].libelle + '">' + result.toys[cpt].libelle + '</option>';
                            cpt++;
                        }
                        updateGiftsForm +=
                                            '</select><br/><br/>' +
                                            '<select id="toy3"  name="toy3">';

                        cpt = 0;
                        for(var toy3 in result.toys)
                        {
                            updateGiftsForm +=
                                                '<option id="' + result.toys[cpt].libelle + '" name="' + result.toys[cpt].libelle + '">' + result.toys[cpt].libelle + '</option>';
                            cpt++;
                        }
                        updateGiftsForm +=
                                            '</select><br/><br/>' +
                                            '<select id="toy4"  name="toy4">';

                        cpt = 0;
                        for(var toy4 in result.toys)
                        {
                            updateGiftsForm +=
                                                '<option id="' + result.toys[cpt].libelle + '" name="' + result.toys[cpt].libelle + '">' + result.toys[cpt].libelle + '</option>';
                            cpt++;
                        }
                        updateGiftsForm +=
                                            '</select><br/><br/>' +
                                            '<select id="toy5"  name="toy5">';

                        cpt = 0;
                        for(var toy5 in result.toys)
                        {
                            updateGiftsForm +=
                                                '<option id="' + result.toys[cpt].libelle + '" name="' + result.toys[cpt].libelle + '">' + result.toys[cpt].libelle + '</option>';
                            cpt++;
                        }

                        updateGiftsForm +=
                                            '</select><br/><br/>' +
                                            '<input id="updateGiftsSubmit" class="btn btn-primary" value="Enregistrer modification" type="submit"> </input>' +
                                        '</fieldset>' +
                                    '</form><br/><br/>';
                        $('#updateGiftsForm').html(updateGiftsForm);
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
                <div id="selectKidForm"></div>
                <div id="addKid" class="addKid">
                    <s:url action="addKid" var="addKid">
                    </s:url>
                    <s:a href="%{addKid}">
                        Ajouter cette enfant
                    </s:a>
                </div>
                <br/>
                <br/>
                <div id="updateGiftsForm"></div>
            </div>
        </div>
    </div>
</body>
</html>
