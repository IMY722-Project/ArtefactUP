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
 * @property {boolean}  started
 * @property {boolean}  completed
 * @property {Step[]}   steps
 */

/**
 * @typedef {Object} HuntStore
 * @property {Hunt[]}  hunts
 * @property {number[]} attemptedHunts
 * @property {(hunts: Hunt[]) => void}           setHunts
 * @property {(huntId: number) => void}          startHunt
 * @property {(huntId: number, stepId: number) => void}  markStepFound
 * @property {(huntId: number, stepId: number) => void}  goToStep
 * @property {(ids: number[]) => void}            setAttemptedHunts
 * @property {(id: number) => void}               addAttemptedHunts
 * @property {() => void}                         clearAttemptedHunts
 * @property {() => void}                         resetAll
 */

export const useHuntStore = create((set, get) => ({
  hunts: [],
  attemptedHunts: [],

  // Initialize hunts with default flags
  setHunts: hunts =>
    set({
      hunts: hunts.map(h => ({
        ...h,
        started: false,
        completed: false,
      })),
    }),

  // Mark a hunt as started and set its first step
  startHunt: huntId =>
    set(state => ({
      hunts: state.hunts.map(h =>
        h.id === huntId
          ? {
              ...h,
              currentStepId: h.steps[0]?.id ?? h.currentStepId,
              started: true,
            }
          : h
      ),
    })),

  // Mark a step as found; if it's the last one, complete the hunt
  markStepFound: (huntId, stepId) =>
    set(state => ({
      hunts: state.hunts.map(h => {
        if (h.id !== huntId) return h;
        const updatedSteps = h.steps.map(s =>
          s.id === stepId ? { ...s, found: true } : s
        );
        const allFound = updatedSteps.every(s => s.found);
        return {
          ...h,
          steps: updatedSteps,
          ...(allFound ? { completed: true } : {}),
        };
      }),
    })),

  // Advance to a specific step
  goToStep: (huntId, stepId) =>
    set(state => ({
      hunts: state.hunts.map(h => {
        if (h.id !== huntId) return h;

        const updatedSteps = h.steps.map(s => ({
          ...s,
          found: s.id < stepId ? true : s.found, // mark steps before stepId as found
        }));

        return {
          ...h,
          steps: updatedSteps,
          currentStepId: stepId,
          started: true,
        };
      }),
    })),

  completeHunt: huntId =>
    set(state => ({
      hunts: state.hunts.map(h => {
        if (h.id !== huntId) return h;

        const updatedSteps = h.steps.map(s => ({ ...s, found: true }));
        return {
          ...h,
          currentStepId: h.steps[0].id,
          steps: updatedSteps,
          started: true,
          completed: true,
        };
      }),
    })),

  setAttemptedHunts: ids => set({ attemptedHunts: ids }),

  addAttemptedHunts: id =>
    set(state => ({
      attemptedHunts: [...state.attemptedHunts, id],
    })),

  clearAttemptedHunts: () => set({ attemptedHunts: [] }),

  // Reset all hunts
  resetAll: () => set({ hunts: [] }),
}));
