// parse 2024-06-13T10:23:09
export const parseDate = (date: string): Date => {
  return new Date(date);
};

export const readableDate = (data: string): string => {
  const date = new Date(data);
  return date.toDateString();
};

export const toJavaDateTime = (date: Date): string => {
  return date.toISOString().slice(0, -1);
};

export const DateRender = (value: string): string => {
  const date = parseDate(value);
  return date.toLocaleDateString() + " " + date.toLocaleTimeString();
};
