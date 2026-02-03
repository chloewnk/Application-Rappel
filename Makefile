SRC = src
BUILD = build
LIB = lib
RES = res
PKG = fr/iutfbleau/papillon

LIBS = $(LIB)/mariadb-java-client-3.3.3.jar
MAIN_CLASS_NAME = fr.iutfbleau.papillon.Main
JAR_NAME = papillon.jar
LIB-EXTRACTED = lib-extracted

# Classes modèles
MODL_GESTIONINDEXETCOULEUR_CLASS = $(BUILD)/$(PKG)/ModeleGestionIndexEtCouleur.class
MODL_RAPPEL_CLASS = $(BUILD)/$(PKG)/ModeleRappel.class
MODL_RAPPELBD_CLASS = $(BUILD)/$(PKG)/ModeleRappelBD.class

# Classes vues et contrôleurs cycliques
VUES_CONTROLEURS_CYCLIC_CLASS = $(BUILD)/$(PKG)/views_and_controllers.done

# Classes principales
MAIN_CLASS = $(BUILD)/$(PKG)/Main.class

# Sources modèles
MODL_GESTIONINDEXETCOULEUR_JAVA = $(SRC)/$(PKG)/ModeleGestionIndexEtCouleur.java
MODL_RAPPEL_JAVA = $(SRC)/$(PKG)/ModeleRappel.java
MODL_RAPPELBD_JAVA = $(SRC)/$(PKG)/ModeleRappelBD.java

# Commande principale
all: init $(MAIN_CLASS)
	jar cvfe $(JAR_NAME) $(MAIN_CLASS_NAME) -C $(BUILD) fr . -C $(LIB-EXTRACTED) . 

# Création dossier build
init:
	@mkdir -p $(BUILD)/$(PKG)

# ---- Compilation des modèles ----
$(MODL_GESTIONINDEXETCOULEUR_CLASS): $(MODL_GESTIONINDEXETCOULEUR_JAVA)
	@echo "Compilation de ModeleGestionIndexEtCouleur"
	@javac -Xlint:unchecked -d $(BUILD) -cp "$(LIB):$(BUILD)" $(MODL_GESTIONINDEXETCOULEUR_JAVA)

$(MODL_RAPPEL_CLASS): $(MODL_RAPPEL_JAVA)
	@echo "Compilation de ModeleRappel"
	@javac -Xlint:unchecked -d $(BUILD) -cp "$(LIB):$(BUILD)" $(MODL_RAPPEL_JAVA)

$(MODL_RAPPELBD_CLASS): $(MODL_RAPPELBD_JAVA) $(MODL_RAPPEL_CLASS)
	@echo "Compilation de ModeleRappelBD"
	@javac -Xlint:unchecked -d $(BUILD) -cp "$(LIB):$(BUILD)" $(MODL_RAPPELBD_JAVA)

# ---- Compilation des vues et contrôleurs cycliques ensemble ----
VUES_CONTROLEURS_CYCLIC_CLASS = $(BUILD)/$(PKG)/views_and_controllers.done

$(VUES_CONTROLEURS_CYCLIC_CLASS): \
    $(SRC)/$(PKG)/VueAfficherRappel.java \
    $(SRC)/$(PKG)/ControleurAfficheRappel.java \
    $(SRC)/$(PKG)/VueModifierRappel.java \
    $(SRC)/$(PKG)/ControleurModifierRappel.java \
	$(SRC)/$(PKG)/ControleurModifierRang.java \
	$(SRC)/$(PKG)/ControleurBtnModifierRappel.java \
	$(SRC)/$(PKG)/VueAjouterRappel.java \
	$(SRC)/$(PKG)/ControleurAjouterRappel.java \
	$(SRC)/$(PKG)/ControleurBtnAjouterRappel.java \
    $(SRC)/$(PKG)/VueRappelPanel.java \
    $(SRC)/$(PKG)/ControleurSupprimerRappel.java \
    $(SRC)/$(PKG)/VuePapillon.java \
    $(SRC)/$(PKG)/ControleurChangerCouleur.java \
    $(MODL_RAPPEL_CLASS) \
    $(MODL_RAPPELBD_CLASS) \
    $(MODL_GESTIONINDEXETCOULEUR_CLASS)
	@echo "Compilation de toutes les vues et contrôleurs cycliques"
	@javac -Xlint:unchecked -d $(BUILD) -cp "$(LIB):$(BUILD)" \
		$(SRC)/$(PKG)/VueAfficherRappel.java \
		$(SRC)/$(PKG)/ControleurAfficheRappel.java \
		$(SRC)/$(PKG)/VueModifierRappel.java \
		$(SRC)/$(PKG)/ControleurModifierRappel.java \
		$(SRC)/$(PKG)/ControleurModifierRang.java \
		$(SRC)/$(PKG)/ControleurBtnModifierRappel.java \
		$(SRC)/$(PKG)/VueAjouterRappel.java \
		$(SRC)/$(PKG)/ControleurAjouterRappel.java \
		$(SRC)/$(PKG)/ControleurBtnAjouterRappel.java \
		$(SRC)/$(PKG)/VueRappelPanel.java \
		$(SRC)/$(PKG)/ControleurSupprimerRappel.java \
		$(SRC)/$(PKG)/VuePapillon.java \
		$(SRC)/$(PKG)/ControleurChangerCouleur.java
	@touch $(VUES_CONTROLEURS_CYCLIC_CLASS)


# Groupe cyclique pour Ajouter
VUES_CONTROLEURS_AJOUT_CLASS = $(BUILD)/$(PKG)/views_controllers_ajout.done

$(VUES_CONTROLEURS_AJOUT_CLASS): $(SRC)/$(PKG)/VueAjouterRappel.java $(SRC)/$(PKG)/ControleurAjouterRappel.java $(SRC)/$(PKG)/ControleurBtnAjouterRappel.java $(MODL_GESTIONINDEXETCOULEUR_CLASS) $(VUES_CONTROLEURS_CYCLIC_CLASS)
	@echo "Compilation de VueAjouterRappel et ControleurAjouterRappel"
	@javac -Xlint:unchecked -d $(BUILD) -cp "$(LIB):$(BUILD)" \
		$(SRC)/$(PKG)/VueAjouterRappel.java \
		$(SRC)/$(PKG)/ControleurAjouterRappel.java \
		$(SRC)/$(PKG)/ControleurBtnAjouterRappel.java 
	@touch $(VUES_CONTROLEURS_AJOUT_CLASS)

# VueRappelPanel et ControleurSupprimerRappel cycliques
VUES_CONTROLEURS_PANEL_CLASS = $(BUILD)/$(PKG)/views_controllers_panel.done

$(VUES_CONTROLEURS_PANEL_CLASS): $(SRC)/$(PKG)/VueRappelPanel.java $(SRC)/$(PKG)/ControleurSupprimerRappel.java $(MODL_RAPPEL_CLASS) $(MODL_RAPPELBD_CLASS) $(MODL_GESTIONINDEXETCOULEUR_CLASS) $(VUES_CONTROLEURS_CYCLIC_CLASS) $(VUES_CONTROLEURS_AJOUT_CLASS)
	@echo "Compilation de VueRappelPanel et ControleurSupprimerRappel"
	@javac -Xlint:unchecked -d $(BUILD) -cp "$(LIB):$(BUILD)" \
		$(SRC)/$(PKG)/VueRappelPanel.java \
		$(SRC)/$(PKG)/ControleurSupprimerRappel.java
	@touch $(VUES_CONTROLEURS_PANEL_CLASS)

# VuePapillon compilé après Panel
$(BUILD)/$(PKG)/VuePapillon.class: $(SRC)/$(PKG)/VuePapillon.java $(VUES_CONTROLEURS_PANEL_CLASS)
	@echo "Compilation de VuePapillon"
	@javac -Xlint:unchecked -d $(BUILD) -cp "$(LIB):$(BUILD)" $(SRC)/$(PKG)/VuePapillon.java

# ControleurChangerCouleur reste séparé
$(BUILD)/$(PKG)/ControleurChangerCouleur.class: $(SRC)/$(PKG)/ControleurChangerCouleur.java $(MODL_GESTIONINDEXETCOULEUR_CLASS)
	@echo "Compilation de ControleurChangerCouleur"
	@javac -Xlint:unchecked -d $(BUILD) -cp "$(LIB):$(BUILD)" $(SRC)/$(PKG)/ControleurChangerCouleur.java

# Main compilé en dernier
$(MAIN_CLASS): $(SRC)/$(PKG)/Main.java $(BUILD)/$(PKG)/VuePapillon.class
	@echo "Compilation de Main"
	@javac -Xlint:unchecked -d $(BUILD) -cp "$(LIB):$(BUILD)" $(SRC)/$(PKG)/Main.java


# pour enlever le dossier build, le jar et la javadoc
clean:
	rm -rf $(BUILD) $(JAR_NAME) doc ; clear

# execution
exec: all
	java -cp "$(BUILD):$(LIBS)" $(MAIN_CLASS_NAME)

# compiler la javadoc
javadoc:
	javadoc -d doc -sourcepath $(SRC) -subpackages fr.iutfbleau.papillon

seedoc: 
	firefox doc/index.html

# executer le .jar
run: all
	java -jar $(JAR_NAME)


#run-jar: jar
#java -cp "$(JAR_NAME):$(LIBS)" $(MAIN_CLASS_NAME)

# PDF pour la compilation latex
LATEX = rapportdoc/rapport.tex
pdf:
	pdflatex $(LATEX)
