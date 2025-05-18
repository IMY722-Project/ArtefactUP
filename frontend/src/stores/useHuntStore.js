// src/stores/useHuntStore.js
import { create } from "zustand";

/**
 * @typedef {Object} Step
 * @property {number}   id
 * @property {boolean}  found
 *
 * @typedef {Object} Hunt
 * @property {number}   id
 * @property {number}   currentStepId
 * @property {Step[]}   steps
 */

/**
 * @typedef {Object} HuntStore
 * @property {Hunt[]}  hunts
 * @property {(hunts: Hunt[]) => void}           setHunts
 * @property {(huntId: number) => void}          startHunt
 * @property {(huntId: number, stepId: number) => void}  markStepFound
 * @property {(huntId: number, stepId: number) => void}  goToStep
 * @property {() => void}                         resetAll
 */

export const useHuntStore = create((set, get) => ({
  hunts: [],

  setHunts: (hunts) => set({ hunts }),

  startHunt: (huntId) =>
    set((state) => ({
      hunts: state.hunts.map((h) =>
        h.id === huntId
          ? { ...h, currentStepId: h.steps[0]?.id ?? h.currentStepId }
          : h
      ),
    })),

  markStepFound: (huntId, stepId) =>
    set((state) => ({
      hunts: state.hunts.map((h) => {
        if (h.id !== huntId) return h;
        return {
          ...h,
          steps: h.steps.map((s) =>
            s.id === stepId ? { ...s, found: true } : s
          ),
        };
      }),
    })),

  goToStep: (huntId, stepId) =>
    set((state) => ({
      hunts: state.hunts.map((h) =>
        h.id === huntId ? { ...h, currentStepId: stepId } : h
      ),
    })),

  resetAll: () => set({ hunts: [] }),
}));
