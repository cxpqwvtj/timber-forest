var Container = React.createClass({
  render: function() {
    return (
      <div>
        <ListBox />
      </div>
    );
  }
});

var ListBox = React.createClass({
  componentDidMount: function(comment) {
    $.ajax({
      url: '/api/filelist',
      dataType: 'json',
      type: 'GET',
      success: function(data) {
        console.log(data);
      }.bind(this),
      error: function(xhr, status, err) {
        console.error(this.props.url, status, err.toString());
        alert(err.toString());
      }.bind(this)
    });
  },
  render: function() {
    return (
      <div>テスト</div>
    );
  }
});

ReactDOM.render(<Container />, document.getElementById("container"));
