<script lang="ts" setup>
import HeaderComp from "@/components/HeaderComp.vue";
import { FontAwesomeIcon } from "@fortawesome/vue-fontawesome";
import {
  faChartLine,
  faConciergeBell,
  faNetworkWired,
  faPlaneDeparture,
  faShield,
  faSuitcaseRolling,
} from "@fortawesome/free-solid-svg-icons";
import TitleComp from "@/components/TitleComp.vue";
</script>

<template>
  <HeaderComp />
  <section class="pt-6 bg-white dark:bg-gray-900">
    <div class="py-6 px-4 mx-auto max-w-screen-xl sm:py-16 lg:px-6">
      <div class="max-w-screen-lg mb-10">
        <div class="flex">
          <h2
            class="max-w-xl mb-4 text-4xl tracking-tight font-extrabold text-gray-900 dark:text-white"
          >
            Introduction to Airport Management System
          </h2>
          <TitleComp class="w-fit text-nowrap my-auto" />
        </div>
        <p class="text-gray-500 sm:text-xl dark:text-gray-400">
          The Airport Management System is designed to streamline and automate
          various operations and processes in an airport. It provides a
          comprehensive solution for managing flights, passenger information,
          baggage handling, security, and more.
        </p>
      </div>
      <div
        class="space-y-8 md:grid md:grid-cols-2 lg:grid-cols-3 md:gap-12 md:space-y-0"
      >
        <div>
          <div
            class="flex justify-center items-center mb-4 w-10 h-10 rounded-full bg-pink-100 lg:h-12 lg:w-12 dark:bg-pink-900"
          >
            <font-awesome-icon
              :icon="faPlaneDeparture"
              class="w-5 h-5 text-pink-600 lg:w-6 lg:h-6 dark:text-pink-300"
            />
          </div>
          <h3 class="mb-2 text-xl font-bold dark:text-white">
            Flight Management
          </h3>
          <p class="text-gray-500 dark:text-gray-400">
            Efficiently manage flight schedules, bookings, and passenger
            information. Ensure smooth operations and timely departures and
            arrivals.
          </p>
        </div>
        <div>
          <div
            class="flex justify-center items-center mb-4 w-10 h-10 rounded-full bg-pink-100 lg:h-12 lg:w-12 dark:bg-pink-900"
          >
            <font-awesome-icon
              :icon="faSuitcaseRolling"
              class="w-5 h-5 text-pink-600 lg:w-6 lg:h-6 dark:text-pink-300"
            />
          </div>
          <h3 class="mb-2 text-xl font-bold dark:text-white">
            Baggage Handling
          </h3>
          <p class="text-gray-500 dark:text-gray-400">
            Efficiently track and manage baggage throughout the airport. Ensure
            accurate handling, sorting, and delivery of passenger luggage.
          </p>
        </div>
        <div>
          <div
            class="flex justify-center items-center mb-4 w-10 h-10 rounded-full bg-pink-100 lg:h-12 lg:w-12 dark:bg-pink-900"
          >
            <font-awesome-icon
              :icon="faShield"
              class="w-5 h-5 text-pink-600 lg:w-6 lg:h-6 dark:text-pink-300"
            />
          </div>
          <h3 class="mb-2 text-xl font-bold dark:text-white">Security</h3>
          <p class="text-gray-500 dark:text-gray-400">
            Ensure the safety and security of passengers, staff, and airport
            facilities. Implement robust security measures and protocols.
          </p>
        </div>
        <div>
          <div
            class="flex justify-center items-center mb-4 w-10 h-10 rounded-full bg-pink-100 lg:h-12 lg:w-12 dark:bg-pink-900"
          >
            <font-awesome-icon
              :icon="faConciergeBell"
              class="w-5 h-5 text-pink-600 lg:w-6 lg:h-6 dark:text-pink-300"
            />
          </div>
          <h3 class="mb-2 text-xl font-bold dark:text-white">
            Customer Service
          </h3>
          <p class="text-gray-500 dark:text-gray-400">
            Enhance passenger experience with excellent customer service.
            Provide support for inquiries, special requests, and assistance.
          </p>
        </div>
        <div>
          <div
            class="flex justify-center items-center mb-4 w-10 h-10 rounded-full bg-pink-100 lg:h-12 lg:w-12 dark:bg-pink-900"
          >
            <font-awesome-icon
              :icon="faNetworkWired"
              class="w-5 h-5 text-pink-600 lg:w-6 lg:h-6 dark:text-pink-300"
            />
          </div>
          <h3 class="mb-2 text-xl font-bold dark:text-white">
            Infrastructure Management
          </h3>
          <p class="text-gray-500 dark:text-gray-400">
            Manage and maintain airport infrastructure. Ensure operational
            efficiency and adherence to safety standards.
          </p>
        </div>
        <div>
          <div
            class="flex justify-center items-center mb-4 w-10 h-10 rounded-full bg-pink-100 lg:h-12 lg:w-12 dark:bg-pink-900"
          >
            <font-awesome-icon
              :icon="faChartLine"
              class="w-5 h-5 text-pink-600 lg:w-6 lg:h-6 dark:text-pink-300"
            />
          </div>
          <h3 class="mb-2 text-xl font-bold dark:text-white">Data Analytics</h3>
          <p class="text-gray-500 dark:text-gray-400">
            Utilize data analytics to make informed decisions. Analyze
            operational data, passenger flow, and other metrics for continuous
            improvement.
          </p>
        </div>
      </div>
    </div>
  </section>
</template>

<style scoped></style>
