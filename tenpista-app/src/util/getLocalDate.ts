export const getLocalDateTime = () => {
  const now = new Date();
  const offset = now.getTimezoneOffset() * 60000; // Offset in milliseconds
  const localISOTime = new Date(now.getTime() - offset)
    .toISOString()
    .slice(0, 16);
  return localISOTime;
};
