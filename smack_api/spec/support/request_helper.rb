# spec/support/request_helper.rb

module Requests  
  module JsonHelpers
    def json
      JSON.parse(last_response.body)
    end
  end
end  