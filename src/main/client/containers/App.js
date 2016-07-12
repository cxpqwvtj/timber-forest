import React, { Component, PropTypes } from 'react'
import { connect } from 'react-redux'
import { browserHistory } from 'react-router'
import { load, resetErrorMessage } from '../actions'

import { MuiThemeProvider, getMuiTheme } from 'material-ui/styles'
import { AppBar, RaisedButton } from 'material-ui'

import injectTapEventPlugin from 'react-tap-event-plugin'
injectTapEventPlugin()

class App extends Component {
  constructor(props) {
    super(props)
    this.handleDismissClick = this.handleDismissClick.bind(this)
    this.buttonClick = this.buttonClick.bind(this)
  }

  buttonClick(e) {
    this.props.load(e, [])
  }

  handleDismissClick(e) {
    this.props.resetErrorMessage()
    e.preventDefault()
  }

  handleChange = (nextValue) => {
    browserHistory.push(`/${nextValue}`)
  }

  renderErrorMessage() {
    const { errorMessage } = this.props
    if (!errorMessage) {
      return null
    }

    return (
      <p style={{ backgroundColor: '#e99', padding: 10 }}>
        <b>{errorMessage}</b>
        {' '}
        (<a href="#"
            onClick={this.handleDismissClick}>
          Dismiss
        </a>)
      </p>
    )
  }

  render() {
    const { children } = this.props
    return (
      <MuiThemeProvider muiTheme={getMuiTheme()}>
        <div>
          <AppBar
            title="timber-forest"
            iconClassNameRight="muidocs-icon-navigation-expand-more"
          />
          <div style={{margin: 10}}>
            <RaisedButton label="テスト" onMouseUp={this.buttonClick} />
          </div>
          <div style={{margin: 10}}>
            <RaisedButton label="ボタン2" />
          </div>
          {children}
          {this.renderErrorMessage()}
        </div>
      </MuiThemeProvider>
    )
  }
}

App.propTypes = {
  // Injected by React Redux
  errorMessage: PropTypes.string,
  resetErrorMessage: PropTypes.func.isRequired,
  inputValue: PropTypes.string.isRequired,
  // Injected by React Router
  children: PropTypes.node,
  load: PropTypes.func.isRequired
}

function mapStateToProps(state, ownProps) {
  console.log('App#mapStateToProps')
  return {
    errorMessage: state.errorMessage,
    inputValue: ownProps.location.pathname.substring(1)
  }
}

export default connect(mapStateToProps, {
  load,
  resetErrorMessage
})(App)
