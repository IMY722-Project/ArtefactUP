import {render, screen} from '@testing-library/react';
import Home from "./Home";

beforeAll(async () => {
  global.TextEncoder = require('util').TextEncoder;
  global.TextDecoder = require('util').TextDecoder;
});

test('TextEncoder is globally defined in Jest', () => {
  expect(global.TextEncoder).toBeDefined();
});

test('renders learn react link', () => {
  render(<Home />);
  const linkElement = screen.getByText(/Deployed React App/i);
  expect(linkElement).toBeInTheDocument();
});
