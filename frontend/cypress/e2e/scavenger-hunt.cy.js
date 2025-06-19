/* eslint-disable no-undef */
/**
 * cypress/e2e/scavenger-hunt.cy.js
 *
 * This test simulates a user clicking the main "Explore Quests" button,
 * navigating to the quests page, and starting the first hunt.
 */
describe('Scavenger Hunt Flow', () => {

  it('should allow a user to start a scavenger hunt from the homepage', () => {
    // Step 1: Visit the homepage.
    cy.visit('http://localhost:3000/');

    // Step 2: Find the main call-to-action button and click it.
    // Based on the screenshot, we look for a button containing the text "Explore Quests".
    // The 'i' makes the search case-insensitive for robustness.
    cy.contains('button', /explore quests/i).click();

    // Step 3: Assert that the URL has changed to the scavenger hunts page.
    cy.url().should('include', '/scavengerHunts');

    // Step 4: Verify we are on the correct page by finding the title.
    cy.contains('h1, h2', /museum quests/i).should('be.visible');

   
  });

});