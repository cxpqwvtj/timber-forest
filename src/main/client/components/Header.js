import React, { Component } from 'react'

import { AppBar } from 'material-ui'

export default class Header extends Component {
  render() {
    return (
      <AppBar
        title="timber-forest"
        iconClassNameRight="muidocs-icon-navigation-expand-more"
      />
    )
  }
}
