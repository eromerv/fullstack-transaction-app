const config = {
  homeUrl: 'http://localhost:3000/',
  api: {
    baseURL: import.meta.env.VITE_API_BASE_URL || 'http://localhost:8080/api',
  },
};

export default config;
