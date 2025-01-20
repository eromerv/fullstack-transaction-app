import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';

const port = process.env.VITE_PORT || '3000';

// https://vite.dev/config/
export default defineConfig({
  plugins: [react()],
  server: {
    port: parseInt(port),
  },
});
