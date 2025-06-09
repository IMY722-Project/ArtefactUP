/* eslint-disable no-undef */
describe('App E2E', () => {
  it('loads page and fetches items', () => {
    cy.visit('/');
    cy.contains('Widgets');
    cy.server();
    cy.route('GET', '/api/widgets', [{id:1,name:'A'}]);
    cy.get('.widget-item').should('have.length', 1);
  });
});
