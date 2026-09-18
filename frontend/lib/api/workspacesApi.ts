import { apiBaseUrl } from './config';
import { toErrorMessage } from './errors';
import type { Workspace } from '@/types/workspace';

const fallbackWorkspaces: Workspace[] = [
  {
    id: 'schedule-foundations',
    name: 'Schedule Foundations',
    description: 'Explore baseline scheduling, staffing, and room-allocation constraints.'
  }
];

export async function getWorkspaces(): Promise<Workspace[]> {
  try {
    const response = await fetch(`${apiBaseUrl}/workspaces`, { cache: 'no-store' });

    if (!response.ok) {
      throw new Error(`Failed to load workspaces (${response.status})`);
    }

    return (await response.json()) as Workspace[];
  } catch (error) {
    console.warn('Falling back to local Harmoni workspace examples:', toErrorMessage(error));
    return fallbackWorkspaces;
  }
}
