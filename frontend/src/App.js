import React from 'react';

function App() {
  return (
      <div style={{ textAlign: 'center', marginTop: '50px' }}>
        <h1>Welcome to Your Deployed React App!</h1>
        <p>This is a simple test page to verify that your React app is working correctly.</p>
        <p>If you see this page, it means your app has been successfully deployed!</p>
        <footer>
          <p>App deployed on EC2 with Docker and Nginx</p>
        </footer>
      </div>
  );
}

export default App;
