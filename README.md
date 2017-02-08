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

L'application est divisée en 3 modules:
* scrumtracker
    * scrumtracker-app
    * scrumtracker-core
    * scrumtracker-web

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
|https://jquery.com |Doit-on vraiment vous présenter JQurey ?| 3.1.1|
|https://momentjs.com |Moment.js nous permet de gérer facilement les conversions de date|2.17.1|
|https://datatables.net|Permet de créer des tableaux dynamiques (recherche, classement, etc)|1.10.13|
|http://codeseven.github.io/toastr|Permet d'afficher des Toasts (notifications) |2.1.3|


## FAQ


## Nous contacter
Pour plus d'informations:
scrumtracker2017@gmail.com
