/// <reference types="cypress" />
// This is a Cypress end-to-end test for a Scavenger Hunt application
describe('Scavenger Hunt App', () => {
  it('loads the homepage', () => {
    cy.visit('http://localhost:3000');
    
  });
});
