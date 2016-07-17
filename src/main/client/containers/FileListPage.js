import React, { Component, PropTypes } from 'react'
import { connect } from 'react-redux'
import { loadLogs } from '../actions'

class FileListPage extends Component {
  static propTypes = {
    loadLogs: PropTypes.func.isRequired,
    logFiles: PropTypes.object.isRequired
  }

 componentDidMount() {
   this.props.loadLogs({endpoint: '/api/filelist'})
 }

  render() {
    const rows = Object.entries(this.props.logFiles).map(([fileName, fileInfo], index) => <tr key={index}><td>{fileName}</td><td>{fileInfo.fileSize}</td><td>{fileInfo.formattedUpdateDate}</td></tr>)
    return (
      <div>
        <table>
          <thead>
            <tr>
              <th>ファイル名</th>
              <th>サイズ(B)</th>
              <th>更新日</th>
            </tr>
          </thead>
          <tbody>{rows}</tbody>
        </table>
      </div>
    )
  }
}

function mapStateToProps(state, ownProps) {
  const logFiles = state.entities.logFile || {}
  return {
    state,
    ownProps,
    logFiles
  }
}

export default connect(mapStateToProps, {
  loadLogs
})(FileListPage)
