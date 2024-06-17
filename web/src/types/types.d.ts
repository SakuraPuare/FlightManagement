export type Pagination = {
  page: number;
  count: number;
};

export type Id = {
  id: number;
};

export type MenuItem = {
  id: number;
  label: string;
  link: string;
  icon: any;
  children: MenuItem[];
};
