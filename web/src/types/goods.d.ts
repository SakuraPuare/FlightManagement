export type Goods = {
  name: string;
  description: string;
  price: number;
  stock: number;
  category: string;
};

export type getGoodsResponse = Id & Goods;

export type createGoodsRequest = Goods;

export type updateGoodsRequest = Partial<Goods>;
