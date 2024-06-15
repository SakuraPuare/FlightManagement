// parse 2024-06-13T10:23:09
export const parseDate = (date: string): Date => {
  return new Date(date);
};

export const ReadableDate = (data: string): string => {
  const date = new Date(data);
  return date.toDateString();
};
