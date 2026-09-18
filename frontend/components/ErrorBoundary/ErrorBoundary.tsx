'use client';

import { Component, ErrorInfo, ReactNode } from 'react';
import ErrorFallback from './ErrorFallback';

type Props = { children: ReactNode };
type State = { hasError: boolean };

export default class ErrorBoundary extends Component<Props, State> {
  state: State = { hasError: false };

  static getDerivedStateFromError(): State {
    return { hasError: true };
  }

  componentDidCatch(_error: Error, _errorInfo: ErrorInfo) {}

  render() {
    if (this.state.hasError) {
      return <ErrorFallback message="Something went wrong." />;
    }

    return this.props.children;
  }
}
