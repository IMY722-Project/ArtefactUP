-- Insert into ScavengerHunt table
INSERT INTO scavenger_hunt (id, name, description)
VALUES (1, 'Ancient Artifacts Hunt', 'Explore ancient artifacts across various museums.');

-- Insert into ScavengerHuntStep table
INSERT INTO scavenger_hunt_step (id, hunt_id, step_number, artefact_id, museum_id, clue)
VALUES (1, 1, 1, 1, 1, 'Find the artifact with the golden crown.'),
       (2, 1, 2, 2, 1, 'Look for the artifact with the ancient script.'),
       (3, 1, 3, 3, 1, 'Discover the artifact hidden in the secret chamber.');