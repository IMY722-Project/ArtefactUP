-- Insert Hunt 1
INSERT INTO scavenger_hunt (id, name, description)
VALUES (1, 'Golden Treasures', 'Hunt for artefacts made of gold across different ages.');

-- Insert Hunt 2
INSERT INTO scavenger_hunt (id, name, description)
VALUES (2, 'Artistic Impressions', 'Track down famous paintings and artistic expressions.');

-- Insert Hunt 3
INSERT INTO scavenger_hunt (id, name, description)
VALUES (3, 'Sculpted Stories', 'Uncover stories carved in stone, bronze, and clay.');

-- Insert Hunt 4
INSERT INTO scavenger_hunt (id, name, description)
VALUES (4, 'Mapungubwe Mysteries', 'Discover secrets from the ancient Mapungubwe civilization.');

-- Insert Hunt 5
INSERT INTO scavenger_hunt (id, name, description)
VALUES (5, 'Dutch Delights', 'Explore artefacts from Dutch origin and influence.');

-- Insert Hunt 6
INSERT INTO scavenger_hunt (id, name, description)
VALUES (6, 'South African Heritage', 'Experience the rich cultural history of South Africa.');

-- Insert Hunt 7
INSERT INTO scavenger_hunt (id, name, description)
VALUES (7, 'Nature in Art', 'Find artefacts inspired by flora and fauna.');

-- Insert Hunt 8
INSERT INTO scavenger_hunt (id, name, description)
VALUES (8, 'Colonial Echoes', 'Trace artefacts from the colonial and postcolonial eras.');

-- Insert Hunt 9
INSERT INTO scavenger_hunt (id, name, description)
VALUES (9, 'Women in History', 'Spotlight artefacts created by or representing women.');

-- Insert Hunt 10
INSERT INTO scavenger_hunt (id, name, description)
VALUES (10, 'Symbolic Ceremonies',
        'Identify objects used in rituals, celebrations, or elite burials.');

INSERT INTO scavenger_hunt_step (id, hunt_id, step_number, artefact_id, museum_id, clue, hint)
VALUES (1, 1, 1, 520, 1, 'Find the artefact titled ''Sleeping Nude'' from 1930.',
        'Created by Maud Sumner and made of Oil on Canvas.');
INSERT INTO scavenger_hunt_step (id, hunt_id, step_number, artefact_id, museum_id, clue, hint)
VALUES (2, 1, 2, 942, 1, 'Find the artefact titled ''Envelope of Prince Willem IV'' from 1739.',
        'Created by William IV and made of Paper.');
INSERT INTO scavenger_hunt_step (id, hunt_id, step_number, artefact_id, museum_id, clue, hint)
VALUES (3, 1, 3, 736, 1, 'Find the artefact titled ''Woudrichem'' from 1926.',
        'Created by Antoon Derkzen van Angeren and made of DrypointEtching.');
INSERT INTO scavenger_hunt_step (id, hunt_id, step_number, artefact_id, museum_id, clue, hint)
VALUES (4, 2, 1, 994, 1, 'Find the artefact titled ''Springbuck'' from 1995.',
        'Created by Manuel Masseka and made of Acrylicon Canvas.');
INSERT INTO scavenger_hunt_step (id, hunt_id, step_number, artefact_id, museum_id, clue, hint)
VALUES (5, 2, 2, 408, 1, 'Find the artefact titled ''Changana Tsonga'' from 1983.',
        'Created by Barbara Tyrrell and made of TemperaonBurlap.');
INSERT INTO scavenger_hunt_step (id, hunt_id, step_number, artefact_id, museum_id, clue, hint)
VALUES (6, 2, 3, 673, 1,
        'Find the artefact titled ''Wilhelmina Wedding anniversary bowl'' from 1926.',
        'Created by CA Langerveld and made of Glass.');
INSERT INTO scavenger_hunt_step (id, hunt_id, step_number, artefact_id, museum_id, clue, hint)
VALUES (7, 3, 1, 667, 1,
        'Find the artefact titled ''Kos is op die Tafel (Dinner is served)'' from 1992.',
        'Created by Stanley Pinker and made of Oil on Canvas.');
INSERT INTO scavenger_hunt_step (id, hunt_id, step_number, artefact_id, museum_id, clue, hint)
VALUES (8, 3, 2, 513, 1, 'Find the artefact titled ''Portrait'' from 1987.',
        'Created by Christo Coetzee and made of Oilon Board.');
INSERT INTO scavenger_hunt_step (id, hunt_id, step_number, artefact_id, museum_id, clue, hint)
VALUES (9, 3, 3, 771, 1, 'Find the artefact titled ''Meeting with Friends'' from 1976.',
        'Created by Eric Mbatha and made of Drypointetching.');
INSERT INTO scavenger_hunt_step (id, hunt_id, step_number, artefact_id, museum_id, clue, hint)
VALUES (10, 4, 1, 134, 1, 'Find the artefact titled ''Musaion'' from 1990.',
        'Created by Andre Otto and made of Bronze.');
INSERT INTO scavenger_hunt_step (id, hunt_id, step_number, artefact_id, museum_id, clue, hint)
VALUES (11, 4, 2, 889, 1, 'Find the artefact titled ''Profile of a Woman'' from 1942.',
        'Created by Ruth Prowse and made of Pencil.');
INSERT INTO scavenger_hunt_step (id, hunt_id, step_number, artefact_id, museum_id, clue, hint)
VALUES (12, 4, 3, 76, 1, 'Find the artefact titled ''House in the woods'' from 1936.',
        'Created by Gregoire Boonzaier and made of Lithograph.');
INSERT INTO scavenger_hunt_step (id, hunt_id, step_number, artefact_id, museum_id, clue, hint)
VALUES (13, 5, 1, 734, 1, 'Find the artefact titled ''Infiltrating Light'' from 1965.',
        'Created by Bettie Cilliers-Barnard and made of Oilon Board.');
INSERT INTO scavenger_hunt_step (id, hunt_id, step_number, artefact_id, museum_id, clue, hint)
VALUES (14, 5, 2, 803, 1, 'Find the artefact titled ''Te Water Gelaat'' from 2013.',
        'Created by Berco WIlsenach and made of Glass.');
INSERT INTO scavenger_hunt_step (id, hunt_id, step_number, artefact_id, museum_id, clue, hint)
VALUES (15, 5, 3, 940, 1,
        'Find the artefact titled ''Archival illustration of clay figurines I'' from 1941.',
        'Created by John Schofield and made of InkonPaper.');
INSERT INTO scavenger_hunt_step (id, hunt_id, step_number, artefact_id, museum_id, clue, hint)
VALUES (16, 6, 1, 901, 1, 'Find the artefact titled ''Commemorative silver trowel'' from 1935.',
        'Created by Travis, Wilson & Co Ltd and made of Silver.');
INSERT INTO scavenger_hunt_step (id, hunt_id, step_number, artefact_id, museum_id, clue, hint)
VALUES (17, 6, 2, 276, 1, 'Find the artefact titled ''Praying Girl'' from 2001.',
        'Created by Yolande van Niekerk and made of Wood.');
INSERT INTO scavenger_hunt_step (id, hunt_id, step_number, artefact_id, museum_id, clue, hint)
VALUES (18, 6, 3, 884, 1, 'Find the artefact titled ''Elza Sullivan Bowl'' from 1990.',
        'Created by Elza Sullivan and made of Stoneware.');
INSERT INTO scavenger_hunt_step (id, hunt_id, step_number, artefact_id, museum_id, clue, hint)
VALUES (19, 7, 1, 568, 1, 'Find the artefact titled ''Ferrie'' from 1965.',
        'Created by Christo Coetzee and made of Oiloncopper.');
INSERT INTO scavenger_hunt_step (id, hunt_id, step_number, artefact_id, museum_id, clue, hint)
VALUES (20, 7, 2, 314, 1, 'Find the artefact titled ''Leopard'' from 1954.',
        'Created by Gerard de Leeuw and made of Bronze.');
INSERT INTO scavenger_hunt_step (id, hunt_id, step_number, artefact_id, museum_id, clue, hint)
VALUES (21, 7, 3, 547, 1, 'Find the artefact titled ''Chinese Qing Bottle Vase'' from 1662/1722.',
        'Created by Unattributed and made of Porcelain.');
INSERT INTO scavenger_hunt_step (id, hunt_id, step_number, artefact_id, museum_id, clue, hint)
VALUES (22, 8, 1, 180, 1,
        'Find the artefact titled ''World War I Dutch Ivora Annexation Plate'' from 1919.',
        'Created by P van der Want and made of Earthenware.');
INSERT INTO scavenger_hunt_step (id, hunt_id, step_number, artefact_id, museum_id, clue, hint)
VALUES (23, 8, 2, 365, 1, 'Find the artefact titled ''The Big Move'' from 2022.',
        'Created by Lelani Nicolaisen and made of Acrylicon Canvas.');
INSERT INTO scavenger_hunt_step (id, hunt_id, step_number, artefact_id, museum_id, clue, hint)
VALUES (24, 8, 3, 526, 1, 'Find the artefact titled ''Highveld Farm'' from 1970.',
        'Created by Adriaan Boshoff and made of Oil on Canvas.');
INSERT INTO scavenger_hunt_step (id, hunt_id, step_number, artefact_id, museum_id, clue, hint)
VALUES (25, 9, 1, 208, 1,
        'Find the artefact titled ''Santa Maria Novella, Florence, Interior'' from 1945.',
        'Created by Giulio Falzoni and made of WatercoloronPaper.');
INSERT INTO scavenger_hunt_step (id, hunt_id, step_number, artefact_id, museum_id, clue, hint)
VALUES (26, 9, 2, 232, 1, 'Find the artefact titled ''The Dagga Smoker'' from 1907.',
        'Created by Anton van Wouw and made of Bronze.');
INSERT INTO scavenger_hunt_step (id, hunt_id, step_number, artefact_id, museum_id, clue, hint)
VALUES (27, 9, 3, 101, 1, 'Find the artefact titled ''District 6 from 1935.''',
        'Created by Gregoire Boonzaier and made of Oil on Canvas.');
INSERT INTO scavenger_hunt_step (id, hunt_id, step_number, artefact_id, museum_id, clue, hint)
VALUES (28, 10, 1, 987, 1,
        'Find the artefact titled ''Marie Roo Breastfeeding Tonny II'' from 1930.',
        'Created by Antoon Derkzen van Angeren and made of DrypointEtching.');
INSERT INTO scavenger_hunt_step (id, hunt_id, step_number, artefact_id, museum_id, clue, hint)
VALUES (29, 10, 2, 916, 1, 'Find the artefact titled ''Zulu beer pot'' from 1980.',
        'Created by Zulu and made of Low-fired Earthenware.');
INSERT INTO scavenger_hunt_step (id, hunt_id, step_number, artefact_id, museum_id, clue, hint)
VALUES (30, 10, 3, 765, 1, 'Find the artefact titled ''Fishing Boats'' from 1962.',
        'Created by Nils Andersen and made of WatercoloronPaper.');