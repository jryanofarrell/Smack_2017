class SmacksController < ApplicationController
    def index
        @smacks = Smack.all()
        render json: {status: 200,
                      data: @smacks}
    end
end
