export const getHeightWithoutHeader = () => {
  const header = document.querySelector("#header");
  const headerHeight = header ? header.clientHeight : 56;
  return window.innerHeight - headerHeight;
};
