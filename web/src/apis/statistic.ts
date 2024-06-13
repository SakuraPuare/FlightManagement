import { AllCountStatisticResponse, TypesStatistic } from "@/types/statistic";
import http from "@/utils/http";

export const getAllCountStatistic =
  async (): Promise<AllCountStatisticResponse> => {
    return http
      .get("/statistics/all-count")
      .then((res) => res.data as AllCountStatisticResponse);
  };

export const getTypesCountStatistic = async (
  type: TypesStatistic
): Promise<AllCountStatisticResponse> => {
  return http
    .get(`/statistics/count/${type}`)
    .then((res) => res.data as AllCountStatisticResponse);
};
