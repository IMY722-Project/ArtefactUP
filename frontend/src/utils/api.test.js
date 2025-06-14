import { fetchWidgets } from './api';
jest.mock('./api');
test('fetchWidgets returns data', async () => {
  fetchWidgets.mockResolvedValue([{ id: 1, name: 'Test' }]);
  const data = await fetchWidgets();
  expect(data).toHaveLength(1);
});
