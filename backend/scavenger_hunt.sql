-- Insert Hunt 1
INSERT INTO scavenger_hunt (id, name, description)
VALUES (1, 'Golden Treasures', 'Hunt for artefacts made of gold across different ages.'),
       (2, 'Artistic Impressions', 'Track down famous paintings and artistic expressions.'),
       (3, 'Sculpted Stories', 'Uncover stories carved in stone, bronze, and clay.'),
       (4, 'Mapungubwe Mysteries', 'Discover secrets from the ancient Mapungubwe civilization.'),
       (5, 'Dutch Delights', 'Explore artefacts from Dutch origin and influence.'),
       (6, 'South African Heritage', 'Experience the rich cultural history of South Africa.'),
       (7, 'Nature in Art', 'Find artefacts inspired by flora and fauna.'),
       (8, 'Colonial Echoes', 'Trace artefacts from the colonial and postcolonial eras.'),
       (9, 'Women in History', 'Spotlight artefacts created by or representing women.'),
       (10, 'Symbolic Ceremonies', 'Identify objects used in rituals, celebrations, or elite burials.'),
       (11, 'The Absurd and the Abstract',
        'Track down pieces that blur reality and embrace the surreal, grotesque, or purely abstract. This hunt will lead you through unexpected forms and philosophies.');

SELECT setval('hunt_id_seq', (SELECT MAX(id) FROM scavenger_hunt));

INSERT INTO scavenger_hunt_step (id, hunt_id, step_number, artefact_id, museum_id, clue, hint)
VALUES (1, 1, 1, (SELECT id FROM artefact WHERE title = 'Sleeping Nude' LIMIT 1), 1,
        'Find the artefact titled ''Sleeping Nude'' from 1930.',
        'Created by Maud Sumner and made of Oil on Canvas.'),

       (2, 1, 2, (SELECT id FROM artefact WHERE title = 'Envelope of Prince Willem IV' LIMIT 1), 1,
        'Find the artefact titled ''Envelope of Prince Willem IV'' from 1739.',
        'Created by William IV and made of Paper.'),

       (3, 1, 3, (SELECT id FROM artefact WHERE title = 'Woudrichem' LIMIT 1), 1,
        'Find the artefact titled ''Woudrichem'' from 1926.',
        'Created by Antoon Derkzen van Angeren and made of DrypointEtching.'),

       (4, 2, 1, (SELECT id FROM artefact WHERE title = 'Springbuck' LIMIT 1), 1,
        'Find the artefact titled ''Springbuck'' from 1995.',
        'Created by Manuel Masseka and made of Acrylicon Canvas.'),

       (5, 2, 2, (SELECT id FROM artefact WHERE title = 'Changana Tsonga' LIMIT 1), 1,
        'Find the artefact titled ''Changana Tsonga'' from 1983.',
        'Created by Barbara Tyrrell and made of TemperaonBurlap.'),

       (6, 2, 3, (SELECT id FROM artefact WHERE title = 'Wilhelmina Wedding anniversary bowl' LIMIT 1), 1,
        'Find the artefact titled ''Wilhelmina Wedding anniversary bowl'' from 1926.',
        'Created by CA Langerveld and made of Glass.'),

       (7, 3, 1, (SELECT id FROM artefact WHERE title = 'Kos is op die Tafel (Dinner is served)' LIMIT 1), 1,
        'Find the artefact titled ''Kos is op die Tafel (Dinner is served)'' from 1992.',
        'Created by Stanley Pinker and made of Oil on Canvas.'),

       (8, 3, 2, (SELECT id FROM artefact WHERE title = 'Portrait' LIMIT 1), 1,
        'Find the artefact titled ''Portrait'' from 1987.',
        'Created by Christo Coetzee and made of Oilon Board.'),

       (9, 3, 3, (SELECT id FROM artefact WHERE title = 'Meeting with Friends' LIMIT 1), 1,
        'Find the artefact titled ''Meeting with Friends'' from 1976.',
        'Created by Eric Mbatha and made of Drypointetching.'),

       (10, 4, 1, (SELECT id FROM artefact WHERE title = 'Musaion' LIMIT 1), 1,
        'Find the artefact titled ''Musaion'' from 1990.',
        'Created by Andre Otto and made of Bronze.'),

       (11, 4, 2, (SELECT id FROM artefact WHERE title = 'Profile of a Woman' LIMIT 1), 1,
        'Find the artefact titled ''Profile of a Woman'' from 1942.',
        'Created by Ruth Prowse and made of Pencil.'),

       (12, 4, 3, (SELECT id FROM artefact WHERE title = 'House in the woods' LIMIT 1), 1,
        'Find the artefact titled ''House in the woods'' from 1936.',
        'Created by Gregoire Boonzaier and made of Lithograph.'),

       (13, 5, 1, (SELECT id FROM artefact WHERE title = 'Infiltrating Light' LIMIT 1), 1,
        'Find the artefact titled ''Infiltrating Light'' from 1965.',
        'Created by Bettie Cilliers-Barnard and made of Oilon Board.'),

       (14, 5, 2, (SELECT id FROM artefact WHERE title = 'Te Water Gelaat' LIMIT 1), 1,
        'Find the artefact titled ''Te Water Gelaat'' from 2013.',
        'Created by Berco WIlsenach and made of Glass.'),

       (15, 5, 3, (SELECT id FROM artefact WHERE title = 'Archival illustration of clay figurines I' LIMIT 1), 1,
        'Find the artefact titled ''Archival illustration of clay figurines I'' from 1941.',
        'Created by John Schofield and made of InkonPaper.'),

       (16, 6, 1, (SELECT id FROM artefact WHERE title = 'Commemorative silver trowel' LIMIT 1), 1,
        'Find the artefact titled ''Commemorative silver trowel'' from 1935.',
        'Created by Travis, Wilson & Co Ltd and made of Silver.'),

       (17, 6, 2, (SELECT id FROM artefact WHERE title = 'Praying Girl' LIMIT 1), 1,
        'Find the artefact titled ''Praying Girl'' from 2001.',
        'Created by Yolande van Niekerk and made of Wood.'),

       (18, 6, 3, (SELECT id FROM artefact WHERE title = 'Elza Sullivan Bowl' LIMIT 1), 1,
        'Find the artefact titled ''Elza Sullivan Bowl'' from 1990.',
        'Created by Elza Sullivan and made of Stoneware.'),

       (19, 7, 1, (SELECT id FROM artefact WHERE title = 'Ferrie' LIMIT 1), 1,
        'Find the artefact titled ''Ferrie'' from 1965.',
        'Created by Christo Coetzee and made of Oiloncopper.'),

       (20, 7, 2, (SELECT id FROM artefact WHERE title = 'Leopard' LIMIT 1), 1,
        'Find the artefact titled ''Leopard'' from 1954.',
        'Created by Gerard de Leeuw and made of Bronze.'),

       (21, 7, 3, (SELECT id FROM artefact WHERE title = 'Chinese Qing Bottle Vase' LIMIT 1), 1,
        'Find the artefact titled ''Chinese Qing Bottle Vase'' from 1662/1722.',
        'Created by Unattributed and made of Porcelain.'),

       (22, 8, 1, (SELECT id FROM artefact WHERE title = 'World War I Dutch Ivora Annexation Plate' LIMIT 1), 1,
        'Find the artefact titled ''World War I Dutch Ivora Annexation Plate'' from 1919.',
        'Created by P van der Want and made of Earthenware.'),

       (23, 8, 2, (SELECT id FROM artefact WHERE title = 'The Big Move' LIMIT 1), 1,
        'Find the artefact titled ''The Big Move'' from 2022.',
        'Created by Lelani Nicolaisen and made of Acrylicon Canvas.'),

       (24, 8, 3, (SELECT id FROM artefact WHERE title = 'Highveld Farm' LIMIT 1), 1,
        'Find the artefact titled ''Highveld Farm'' from 1970.',
        'Created by Adriaan Boshoff and made of Oil on Canvas.'),

       (25, 9, 1, (SELECT id FROM artefact WHERE title = 'Santa Maria Novella, Florence, Interior' LIMIT 1), 1,
        'Find the artefact titled ''Santa Maria Novella, Florence, Interior'' from 1945.',
        'Created by Giulio Falzoni and made of WatercoloronPaper.'),

       (26, 9, 2, (SELECT id FROM artefact WHERE title = 'The Dagga Smoker' LIMIT 1), 1,
        'Find the artefact titled ''The Dagga Smoker'' from 1907.',
        'Created by Anton van Wouw and made of Bronze.'),

       (27, 9, 3, (SELECT id FROM artefact WHERE title = 'District 6' LIMIT 1), 1,
        'Find the artefact titled ''District 6 from 1935.''',
        'Created by Gregoire Boonzaier and made of Oil on Canvas.'),

       (28, 10, 1, (SELECT id FROM artefact WHERE title = 'Marie Roo Breastfeeding Tonny II' LIMIT 1), 1,
        'Find the artefact titled ''Marie Roo Breastfeeding Tonny II'' from 1930.',
        'Created by Antoon Derkzen van Angeren and made of DrypointEtching.'),

       (29, 10, 2, (SELECT id FROM artefact WHERE title = 'Zulu beer pot' LIMIT 1), 1,
        'Find the artefact titled ''Zulu beer pot'' from 1980.',
        'Created by Zulu and made of Low-fired Earthenware.'),

       (30, 10, 3, (SELECT id FROM artefact WHERE title = 'Fishing Boats' LIMIT 1), 1,
        'Find the artefact titled ''Fishing Boats'' from 1962.',
        'Created by Nils Andersen and made of WatercoloronPaper.'),

       (31, 11, 1, (SELECT id FROM artefact WHERE title = 'Emotional Scars' LIMIT 1), 3,
        'Find an artwork that explores trauma through sharp and powerful impressions.',
        'A linocut titled ''Emotional Scars'' by Blessing Ngobeni created in 2020.'),

       (32, 11, 2, (SELECT id FROM artefact WHERE title = 'City Deep' LIMIT 1), 2,
        'Look for a miner brought to life through bold, cartoonish exaggeration.',
        'Sculpted by Norman Catherine in 1996 using painted resin.'),

       (33, 11, 3, (SELECT id FROM artefact WHERE title = 'Nihilist Calendar' LIMIT 1), 3,
        'Track down a calendar that celebrates chaos and irony.',
        'A ceramic coaster by Francke Gretchen Crots from 2018.'),

       (34, 11, 4, (SELECT id FROM artefact WHERE title = 'Woven Abstract' LIMIT 1), 4,
        'Search for woven chaos made for the avant-garde.',
        'Christo Coetzee’s mixed-media work from 1984 created for a show in Taiwan.');


SELECT setval('step_id_seq', (SELECT MAX(id) FROM scavenger_hunt_step));



-- new scavenger hunt
-- Insert Scavenger Hunt

