[![Codacy Badge](https://api.codacy.com/project/badge/Grade/48babfc088e24ae9ba9ea6e64f2c9693)](https://www.codacy.com/app/michel/scrumtracker2017?utm_source=github.com&utm_medium=referral&utm_content=tyrcho/scrumtracker2017&utm_campaign=badger)
[![Build Status](https://travis-ci.org/tyrcho/scrumtracker2017.svg?branch=master)](https://travis-ci.org/tyrcho/scrumtracker2017)

Démarrage local : 

    mvn package
    java -jar target/dependency/webapp-runner.jar target/*.war

# SCRUMTRACKER2017
Scrumtracker est une application web permettant la gestion de Sprints, Stories et tâches.

## L'équipe

* BELOSO Juan
* DELIGAND Audrey
* DOUVILLE DE FRANSSU Charles
* GILLOTIN Thibault
* LEQUIN Nicolas
* MIGUET Anaick
* PICART David
* ROQUEPLO Thomas

## Fonctionnement du projet

Description à réaliser. Test Audrey.

## Technologies utilisées
Le framework Spring est une boite à outils très riche permettant de structurer, 
d'améliorer et de simplifier l'écriture d'application JEE.

### Spring MVC

### Thymeleaf
[Thymeleaf](http://www.thymeleaf.org/) est un générateur de Template.
Une fois configuré, son fonctionnement est très simple. 
Il suffit de respecter ces x étapes:
 * Dans l'un des contrôleurs, ajouter un objet au 'Model'
```
@RequestMapping({"/*","/home"})
    public String goHome(Model model, HttpServletRequest request, HttpServletResponse reponse)
    {
        Sprint sprintTest = new Sprint();
        sprintTest.setNom("Hello World d'un Sprint");
    
        model.addAttribute("ObjetTest", sprintTest);
    
        return "pageDeTest";
    }
```
   On ajoute l'objet 'ObjetTest' au model.
   Le contrôleur renvoie alors une string du nom de la page qui doit être chargée.
   Ici, la page chargée sera pageDeTest.html
   
  * Créer la page 'pageDeTest.html' dans le dossier WEB-INF/templates. Cette page contiendra des balises html ainsi que des tags propres à Thymeleaf.
   
```
  <div class="uneClasseBootstrap" th:text="${model.ObjetTest.nom}">
    Texte remplacé par le texte de la balise th:text
  </div>
```

Prêtez attention au tag `th:text`. Le contenu des tags précédés de `th` est interprété par Thymeleaf. En respectant la syntaxe de Thymeleaf, on peut directement accéder aux propriétés des objets placés dans le `model`.
La balise précédente affichera donc le texte suivant:
```
    Hello World d'un Sprint
```

Thymeleaf est un outil très puissant, il permet entre autre d'effectuer des boucles sur des listes d'objets etc etc. 
Si vous souhaitez vous familiariser avec Thymeleaf, un tutoriel en ligne est disponible [ici](http://itutorial.thymeleaf.org/).

### Bootstrap

###  Librairies JS
| Librairie | Description|Version |
|-----------|---------|---------|
|https://jquery.com |Doit-on vraiment vous présenter JQuery ?| 3.1.1|
|https://momentjs.com |Moment.js nous permet de gérer facilement les conversions de date|2.17.1|
|https://datatables.net|Permet de créer des tableaux dynamiques (recherche, classement, etc)|1.10.13|
|http://codeseven.github.io/toastr|Permet d'afficher des Toasts (notifications) |2.1.3|

#### Toastr.js
Si vous devez afficher des notifications à l'utilisateur il y a Toastr ! 
<br>
Utile lors des requêtes AJAX par exemple.
<br>
`toastr.success("Texte");`
<br>
`toastr.info("Texte");`
<br>
`toastr.warning("Texte");`
<br>
`toastr.error("Texte");`
<br>
Pour plus d'infos: c'est ici: http://codeseven.github.io/toastr/demo.html
   



## Commandes CLI utiles
| Commande | Description|
|----------|------------|
|`heroku info scrumtracker2017`| Affichage des infos concernant l'application|
|`heroku logs --app scrumtracker2017 --tail`| Affichage du log de l'application (CTRL + C pour break)|
|`heroku ps:scale web=1 --app scrumtracker2017`| Active l'app en temps qu'appli web|
|`heroku run env --app scrumtracker2017`| Affiche les variables locales définies sur le serveur|
## FAQ


## Nous contacter
Pour plus d'informations:
scrumtracker2017@gmail.com
