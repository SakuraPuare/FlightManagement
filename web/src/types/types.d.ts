/*
 * Copyright (c) 2024. All right reserved by SakuraPuare.
 */

import { IconDefinition } from "@fortawesome/fontawesome-svg-core";

export type Pagination = {
  page: number;
  count: number;
};

export type Id = {
  ids: number;
};

export type MenuItem = {
  id: number;
  label: string;
  link: string;
  icon: IconDefinition;
  children: MenuItem[];
};
